package vn.edu.stu.laptopshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.stu.laptopshop.common.OrderStatus;
import vn.edu.stu.laptopshop.common.UserType;
import vn.edu.stu.laptopshop.controller.request.order.*;
import vn.edu.stu.laptopshop.exception.InvalidDataException;
import vn.edu.stu.laptopshop.exception.ResourceNotFoundException;
import vn.edu.stu.laptopshop.model.OrderDetailEntity;
import vn.edu.stu.laptopshop.model.OrderEntity;
import vn.edu.stu.laptopshop.model.ProductEntity;
import vn.edu.stu.laptopshop.model.UserEntity;
import vn.edu.stu.laptopshop.repository.OrderDetailRepository;
import vn.edu.stu.laptopshop.repository.OrderRepository;
import vn.edu.stu.laptopshop.repository.ProductRepository;
import vn.edu.stu.laptopshop.repository.UserRepository;
import vn.edu.stu.laptopshop.service.OrderService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderEntity addOrder(OrderCreateRequest request) {
        UserEntity userEntity = userRepository.findById(request.getUserId()).orElseThrow(()->
                new ResourceNotFoundException("User id" + request.getUserId() + " not found"));

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(userEntity);
        orderEntity.setOrderDate(LocalDateTime.now());
        orderEntity.setStatus(OrderStatus.PENDING);

        if(userEntity.getAddress().isEmpty()){
            throw new InvalidDataException("User please update delivery address");
        }
        if(userEntity.getPhone().isEmpty()){
            throw new InvalidDataException("Users please update phone number to receive goods");
        }

        double totalAmount = 0;
        List<OrderDetailEntity> listOrderDetail = new ArrayList<>();
        for(OrderItemRequest order : request.getItems()) {
            ProductEntity productEntity = productRepository.findById(order.getProductId()).orElseThrow(()->
                    new ResourceNotFoundException("Product id" + order.getProductId() + " not found"));

            if(productEntity.getStockQuantity() < order.getQuantity()) {
                throw new InvalidDataException("Product " + productEntity.getName() + "out of stock");
            }
            productEntity.setStockQuantity(productEntity.getStockQuantity() - order.getQuantity());
            productRepository.save(productEntity);

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setProduct(productEntity);
            orderDetailEntity.setOrder(orderEntity);
            orderDetailEntity.setQuantity(order.getQuantity());
            orderDetailEntity.setUnitPrice(productEntity.getPrice());
            orderDetailRepository.save(orderDetailEntity);

            totalAmount += productEntity.getPrice() * order.getQuantity();
            listOrderDetail.add(orderDetailEntity);
        }
        orderEntity.setTotalAmount(totalAmount);
        orderEntity.setOrderDetails(listOrderDetail);

        orderRepository.save(orderEntity);

        return orderEntity;
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order id" + id + " not found"));
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User id" + userId + " not found"));
        if(userEntity != null && !userEntity.getOrders().isEmpty()) {
            return userEntity.getOrders();
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public OrderEntity cancelOrder(CancelOrderRequest request) {
        OrderEntity orderEntity = getOrderById(request.getOrderId());

        if(!orderEntity.getUser().getId().equals(request.getUserId())) {
            throw new InvalidDataException("You do not have the right to cancel the order," +
                    " only the owner has the right to cancel the order.");
        }

        if(orderEntity.getStatus() != OrderStatus.PENDING && orderEntity.getStatus() != OrderStatus.CONFIRMED) {
            throw new InvalidDataException("This order cannot be canceled at the current stage.");
        }

        if(!orderEntity.getOrderDetails().isEmpty()) {
            for(OrderDetailEntity orderDetail : orderEntity.getOrderDetails()) {
                ProductEntity product = orderDetail.getProduct();
                product.setStockQuantity(product.getStockQuantity() + orderDetail.getQuantity());
                productRepository.save(product);
            }
        }
        orderEntity.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(orderEntity);

        return orderEntity;
    }

    @Override
    public OrderEntity confirmOrder(ConfirmOrderRequest request) {
        OrderEntity orderEntity = getOrderById(request.getOrderId());
        UserEntity user = userRepository.findById(request.getAdminId()).orElseThrow(()->
                new ResourceNotFoundException("Admin id" + request.getAdminId() + " not found"));

        if (user.getType() != UserType.ADMIN) {
            throw new InvalidDataException("Only an Admin can confirm the order.");
        }

        if (orderEntity.getStatus() != OrderStatus.PENDING) {
            throw new InvalidDataException("The order is not in PENDING status, cannot confirm.");
        }

        orderEntity.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(orderEntity);

        return orderEntity;
    }


    @Override
    public OrderEntity confirmReceiveOrder(OrderConfirmReceiveRequest request) {
        OrderEntity orderEntity = getOrderById(request.getOrderId());
        UserEntity user = orderEntity.getUser();

        if(user == null || !user.getId().equals(request.getUserId())) {
            throw new InvalidDataException("You can only confirm your own order.");
        }
        if(orderEntity.getStatus() != OrderStatus.SHIPPED){
            throw new InvalidDataException("The order is not in SHIPPED status, cannot confirm.");
        }
        orderEntity.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(orderEntity);

        return orderEntity;
    }

}

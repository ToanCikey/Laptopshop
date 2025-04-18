package vn.edu.stu.laptopshop.service;

import vn.edu.stu.laptopshop.controller.request.order.CancelOrderRequest;
import vn.edu.stu.laptopshop.controller.request.order.ConfirmOrderRequest;
import vn.edu.stu.laptopshop.controller.request.order.OrderConfirmReceiveRequest;
import vn.edu.stu.laptopshop.controller.request.order.OrderCreateRequest;
import vn.edu.stu.laptopshop.model.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity addOrder(OrderCreateRequest request);
    List<OrderEntity> getAllOrders();
    OrderEntity getOrderById(Long id);
    List<OrderEntity> getOrdersByUserId(Long userId);
    OrderEntity cancelOrder(CancelOrderRequest request);
    OrderEntity confirmOrder(ConfirmOrderRequest request);
    OrderEntity confirmReceiveOrder(OrderConfirmReceiveRequest request);
}

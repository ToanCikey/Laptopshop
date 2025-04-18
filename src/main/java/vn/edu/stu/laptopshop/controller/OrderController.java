package vn.edu.stu.laptopshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.laptopshop.controller.request.order.CancelOrderRequest;
import vn.edu.stu.laptopshop.controller.request.order.ConfirmOrderRequest;
import vn.edu.stu.laptopshop.controller.request.order.OrderConfirmReceiveRequest;
import vn.edu.stu.laptopshop.controller.request.order.OrderCreateRequest;
import vn.edu.stu.laptopshop.controller.response.ResponseSuccess;
import vn.edu.stu.laptopshop.mapper.OrderMapper;
import vn.edu.stu.laptopshop.model.OrderEntity;
import vn.edu.stu.laptopshop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Tag(name = "Order Controller")
@Slf4j(topic = "ORDER-CONTROLLER")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(summary = "Create order", description = "API create order to database")
    @PostMapping("/create")
    public ResponseSuccess<?> createOrder(@Valid @RequestBody OrderCreateRequest request){
        OrderEntity orderEntity = orderService.addOrder(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(), "Create order successful", orderMapper.toOrderResponse(orderEntity));
    }

    @Operation(summary = "Get order by id", description = "API get order by id to database")
    @GetMapping("/{id}")
    public ResponseSuccess<?> getOrderById(@Min(1) @PathVariable Long id){
        OrderEntity orderEntity = orderService.getOrderById(id);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get order by id successful", orderMapper.toOrderResponse(orderEntity));
    }

    @Operation(summary = "Get all order by user id", description = "API get all order by user id to database")
    @GetMapping("/order-user/{userId}")
    public ResponseSuccess<?> getAllOrderByUserId(@Min(1) @PathVariable Long userId){
        List<OrderEntity> orderEntities = orderService.getOrdersByUserId(userId);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get all order by user id successful", orderMapper.toOrderResponseList(orderEntities));
    }

    @Operation(summary = "Cancel order", description = "API cancel order to database")
    @PostMapping("/cancel-order")
    public ResponseSuccess<?> cancelOrder(@Valid @RequestBody CancelOrderRequest request){
        OrderEntity entity = orderService.cancelOrder(request);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Cancel order successful", orderMapper.toCancelOrderResponse(entity));
    }

    @Operation(summary = "Confirm order by admin", description = "API confirm order by admin to database")
    @PostMapping("/confirm-order")
    public ResponseSuccess<?> confirmOrderByAdmin(@Valid @RequestBody ConfirmOrderRequest request){
        OrderEntity entity = orderService.confirmOrder(request);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Confirm order by admin successful", orderMapper.toConfirmOrderResponse(entity));
    }

    @Operation(summary = "Confirm receive order by user", description = "API confirm receive order by user to database")
    @PostMapping("/confirm-receive")
    public ResponseSuccess<?> confirmReceiveOrder(@Valid @RequestBody OrderConfirmReceiveRequest request){
        OrderEntity entity = orderService.confirmReceiveOrder(request);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Confirm receive order by user successful", orderMapper.toConfirmOrderReceiveResponse(entity));
    }

}

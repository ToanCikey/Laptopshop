package vn.edu.stu.laptopshop.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.laptopshop.controller.response.order.*;
import vn.edu.stu.laptopshop.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderResponse toOrderResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = new OrderResponse();
        modelMapper.map(orderEntity, orderResponse);

        if(orderEntity.getUser() != null) {
            orderResponse.setUserId(orderEntity.getUser().getId());
        }
        if (orderEntity.getOrderDetails() != null && !orderEntity.getOrderDetails().isEmpty()) {
            orderResponse.setOrderDetails(orderEntity.getOrderDetails().stream()
                    .map(order -> modelMapper.map(order, OrderDetailResponse.class))
                    .toList());
        }
        return orderResponse;
    }

    public List<OrderResponse> toOrderResponseList(List<OrderEntity> orderEntityList) {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntityList) {
            orderResponseList.add(toOrderResponse(orderEntity));
        }
        return orderResponseList;
    }

    public CancelOrderResponse toCancelOrderResponse(OrderEntity orderEntity) {
        CancelOrderResponse cancelOrderResponse = new CancelOrderResponse();
        cancelOrderResponse.setOrderId(orderEntity.getId());
        cancelOrderResponse.setOrderStatus(orderEntity.getStatus().toString());

        return cancelOrderResponse;
    }

    public ConfirmOrderResponse toConfirmOrderResponse(OrderEntity orderEntity) {
        ConfirmOrderResponse confirmOrderResponse = new ConfirmOrderResponse();
        confirmOrderResponse.setOrderId(orderEntity.getId());
        confirmOrderResponse.setOrderStatus(orderEntity.getStatus().toString());

        return confirmOrderResponse;
    }

    public ConfirmOrderReceiveResponse toConfirmOrderReceiveResponse(OrderEntity orderEntity) {
        ConfirmOrderReceiveResponse receiveResponse = new ConfirmOrderReceiveResponse();
        receiveResponse.setOrderId(orderEntity.getId());
        receiveResponse.setOrderStatus(orderEntity.getStatus().toString());

        return receiveResponse;
    }

}

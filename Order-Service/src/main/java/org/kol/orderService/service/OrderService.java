package org.kol.orderService.service;
import org.kol.orderService.entity.Order;
import org.kol.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
      Order savedOrder=  orderRepository.save(order);

    //  OrderResponse saveOrderResponse=modelMapper.map(saveOrder,OrderResponse.class);
      return savedOrder;
    }

    public Order updateOrder(Long orderId, Order order) {

        Order savedOrder=orderRepository.findById(orderId).get();

        savedOrder.setId(order.getId());
        savedOrder.setName(order.getName());
        savedOrder.setType(order.getType());
        savedOrder.setProductName(order.getProductName());

        Order updateOrder=orderRepository.save(savedOrder);

       // OrderResponse updateOrderResponse=modelMapper.map(updateOrder,OrderResponse.class);

        return updateOrder;

    }

    public Order findOrdersById(Long orderId) {

        Order findOrderById=orderRepository.findById(orderId).get();

     //   OrderResponse findOrderResponse=modelMapper.map(findOrderById,OrderResponse.class);

        return findOrderById;

    }

    public List<Order> findAllOrders() {

        List<Order> fetchAllOrders=orderRepository.findAll();

//       List<OrderResponse> orderResponse = fetchAllOrder
//               .stream()
//               .map(orderResponses -> modelMapper.map(orderResponses, OrderResponse.class))
//               .collect(Collectors.toList());

        return fetchAllOrders;

    }


    public String DeleteOrder(Long orderId) {

        orderRepository. deleteById(orderId);
        return "Order Deleted";
    }



}

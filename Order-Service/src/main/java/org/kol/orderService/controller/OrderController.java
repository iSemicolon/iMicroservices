package org.kol.orderService.controller;

import org.kol.orderService.entity.Order;
import org.kol.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create-order")
    public Order addOrder(@RequestBody Order order) {

        Order savedOrder = orderService.saveOrder(order);

        return savedOrder;

    }


    @PutMapping("/update-order/{Id}")
    public Order updateOrder(@PathVariable("Id") Long orderId, @RequestBody Order order) {

        Order orderResponse = orderService.updateOrder(orderId, order);

        return orderResponse;
    }

    @GetMapping("/fetch-order-by-id/{Id}")
    public Order getOrderById(@PathVariable("Id") Long orderId) {

        Order orderResponse = orderService.findOrdersById(orderId);

        return orderResponse;
    }


    @GetMapping("/fetch-all-orders")
    public List<Order> getAllOrder() {

        List<Order> orderResponse = orderService.findAllOrders();

        return orderResponse;

    }


    @DeleteMapping("remove-order-by-id/{Id}")
    public String DeleteOrderById(@PathVariable("Id") Long orderId) {

        String deleteMsg = orderService.DeleteOrder(orderId);

        return deleteMsg;
    }

}

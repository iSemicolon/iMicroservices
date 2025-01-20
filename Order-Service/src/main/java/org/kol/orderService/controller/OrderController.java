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

    //http://localhost:9091/order/create-order
    @PostMapping("/create-order")
    public Order addOrder(@RequestBody Order order) {

        Order savedOrder = orderService.saveOrder(order);

        return savedOrder;
    }

    //http://localhost:9091/order/update-order/2
    @PutMapping("/update-order/{Id}")
    public Order updateOrder(@PathVariable("Id") Long orderId, @RequestBody Order order) {

        Order orderResponse = orderService.updateOrder(orderId, order);

        return orderResponse;
    }

    //http://localhost:9091/order/fetch-order-by-id/2
    @GetMapping("/fetch-order-by-id/{Id}")
    public Order getOrderById(@PathVariable("Id") Long orderId) {

        Order orderResponse = orderService.findOrdersById(orderId);

        return orderResponse;
    }

    //http://localhost:9091/order/fetch-all-orders
    @GetMapping("/fetch-all-orders")
    public List<Order> getAllOrder() {

        List<Order> orderResponse = orderService.findAllOrders();

        return orderResponse;

    }

    //http://localhost:9091/order/remove-order-by-id/3
    @DeleteMapping("remove-order-by-id/{Id}")
    public String DeleteOrderById(@PathVariable("Id") Long orderId) {

        String deleteMsg = orderService.DeleteOrder(orderId);

        return deleteMsg;
    }

    //http://localhost:9091/order/create-order-using-rest-template
    @PostMapping("/create-order-using-rest-template")
    public Order addOrderRestTemplate(@RequestBody Order order) {

        Order savedOrder = orderService.saveRestTemplateOrder(order);

        return savedOrder;
    }

    //http://localhost:9091/order/create-order-using-feign-client
    @PostMapping("/create-order-using-feign-client")
    public Order addOrderFeignClient(@RequestBody Order order) {

        Order savedOrder = orderService.saveFeignCreateOrder(order);

        return savedOrder;
    }

    //http://localhost:9091/order/create-order-using-web-client
    @PostMapping("/create-order-using-web-client")
    public Order addOrderWebClient(@RequestBody Order order) {

        Order savedOrder = orderService.webCreateOrder(order).block();

        return savedOrder;
    }

}

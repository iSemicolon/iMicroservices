package org.kol.orderService.service;

import org.kol.orderService.entity.Order;
import org.kol.orderService.feignClient.ProductServiceClient;
import org.kol.orderService.repository.OrderRepository;
import org.kol.orderService.response.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductServiceClient productServiceClient;

    private final String PRODUCT_SERVICE_URL = "http://localhost:9090/product";
    private final WebClient webClient;

    public Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        return savedOrder;
    }

    public Order updateOrder(Long orderId, Order order) {

        Order savedOrder = orderRepository.findById(orderId).get();

        savedOrder.setId(order.getId());
        savedOrder.setName(order.getName());
        savedOrder.setType(order.getType());
        savedOrder.setProductName(order.getProductName());

        Order updateOrder = orderRepository.save(savedOrder);

        return updateOrder;

    }

    public Order findOrdersById(Long orderId) {

        Order findOrderById = orderRepository.findById(orderId).get();

        return findOrderById;

    }

    public List<Order> findAllOrders() {

        List<Order> fetchAllOrders = orderRepository.findAll();

        return fetchAllOrders;

    }


    public String DeleteOrder(Long orderId) {

        orderRepository.deleteById(orderId);
        return "Order Deleted";
    }


    public Product getProductById(Long productId) {
        String url = PRODUCT_SERVICE_URL + "/fetch-product-by-id/" + productId;
        return restTemplate.getForObject(url, Product.class);
    }

    public Order saveRestTemplateOrder(Order order) {

        Product product = getProductById(order.getId());
        if (product != null) {
            order.setProductName(product.getName());
        }
        orderRepository.save(order);

        return order;
    }


    public Order saveFeignCreateOrder(Order order) {

        Product product = productServiceClient.getProductById(order.getId());
        if (product != null) {
            order.setProductName(product.getName());
        }
        orderRepository.save(order);

        return order;
    }


    public OrderService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9090").build(); // Adjust the base URL as needed
    }

    public Mono<Order> webCreateOrder(Order order) {
        return webClient.get()
                .uri("/product/fetch-product-by-id/{productId}", order.getId())
                .retrieve()
                .bodyToMono(Product.class)
                .map(product -> {
                    order.setProductName(product.getName());
                    orderRepository.save(order);
                    return order;
                });
    }

}

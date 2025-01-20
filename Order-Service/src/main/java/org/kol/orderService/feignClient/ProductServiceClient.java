package org.kol.orderService.feignClient;

import org.kol.orderService.response.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:9090") // Adjust the URL as needed
public interface ProductServiceClient {
    @GetMapping("/product/fetch-product-by-id/{productId}")
    Product getProductById(@PathVariable("productId") Long productId);

}

/* Feign input
{
    "productId": 1,  // Replace with an actual product ID that exists in the Product-Service
    "name": "Sample Order",  // Optional field for the order name
    "type": "Online"  // Optional field for the order type
}
 */
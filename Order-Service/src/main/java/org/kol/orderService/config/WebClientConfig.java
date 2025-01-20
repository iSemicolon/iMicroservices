package org.kol.orderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

/* Web Client input
{
    "productId": 1,  // Replace with an actual product ID that exists in the Product-Service
    "name": "Sample Order",  // Optional field for the order name
    "type": "Online"  // Optional field for the order type
}
 */

package org.kol.orderService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

/* Rest Template input
{
    "productId": 1,  // Replace with an actual product ID that exists in the Product-Service
    "name": "Sample Order",  // Optional field for the order name
    "type": "Online"  // Optional field for the order type
}
 */

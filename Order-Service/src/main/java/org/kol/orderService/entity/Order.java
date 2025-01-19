package org.kol.orderService.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "Order")
public class Order {

    private Long id;
    private String name;
    private String type;
    private String productName;

}

package org.kol.orderService.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Product {

    private Long id;
    private String name;
    private String category;

}

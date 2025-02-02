package org.kol.productService.utils;

import org.kol.productService.entity.Product;

public class ProductUtils {

    public static Product createProducts() {
        return Product.builder()
                .id(80L)
                .name("Audi")
                .category("Vehicle")
                .build();
    }
}

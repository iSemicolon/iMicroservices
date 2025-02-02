package org.kol.productService.controller;

import org.kol.productService.entity.Product;
import org.kol.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    //http://localhost:9090/product/create-product
    @PostMapping("/create-product")
    public Product addProduct(@RequestBody Product product) {

        Product savedProduct = productService.saveProduct(product);

        return savedProduct;

    }

    //http://localhost:9090/product/update-product/53
    @PutMapping("/update-product/{Id}")
    public Product updateProductById(@PathVariable("Id") Long productId, @RequestBody Product product) {

        Product productResponse = productService.updateProduct(productId, product);

        return productResponse;
    }

    //http://localhost:9090/product/fetch-product-by-id/2
    @GetMapping("/fetch-product-by-id/{Id}")
    public Product getProductById(@PathVariable("Id") Long productId) {

        Product productResponse = productService.findProductById(productId);

        return productResponse;

    }


    //http://localhost:9090/product/fetch-all-products
    @GetMapping("/fetch-all-products")
    public List<Product> getAllProducts() {

        List<Product> productResponse = productService.findAllProducts();

        return productResponse;

    }

    //http://localhost:9090/product/remove-product-by-id/52
    @DeleteMapping("remove-product-by-id/{Id}")
    public String deleteProductById(@PathVariable("Id") Long productId) {

        String deleteMsg = productService.DeleteOrder(productId);

        return deleteMsg;
    }

}

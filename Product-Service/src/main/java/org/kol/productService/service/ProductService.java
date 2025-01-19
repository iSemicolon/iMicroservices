package org.kol.productService.service;

import org.kol.productService.entity.Product;
import org.kol.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    public Product saveProduct(Product product) {
        Product saveProduct=  productRepository.save(product);

      return saveProduct;
    }

    public Product updateProduct(Long productId, Product product) {

        Product savedProduct=productRepository.findById(productId).get();

        savedProduct.setId(product.getId());
        savedProduct.setName(product.getName());
        savedProduct.setCategory(product.getCategory());

        Product updateProduct=productRepository.save(savedProduct);

        return updateProduct;
    }

    public Product findProductById(Long productId) {

        Product findOrderById=productRepository.findById(productId).get();

        return findOrderById;

    }

    public List<Product> findAllProducts() {

        List<Product> findAllProducts=productRepository.findAll();

        return findAllProducts;

    }


    public String DeleteOrder(Long orderId) {

        productRepository. deleteById(orderId);

        return "Record Deleted";
    }
}

package org.kol.productService.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kol.productService.entity.Product;
import org.kol.productService.repository.ProductRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.kol.productService.utils.ProductUtils.createProducts;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("service: saveProduct")
    public void givenProduct_whenSaveProductCalled_thenProductIsSaved() {

        Product createProduct = createProducts();

        //given
        when(productRepository.save(any(Product.class))).thenReturn(createProduct);
        //when

        Product savedProduct = productService.saveProduct(createProduct);

        //then

        assertNotNull(savedProduct);
        assertEquals(80L, savedProduct.getId());
        assertEquals("Audi", savedProduct.getName());
        assertEquals("Vehicle", savedProduct.getCategory());

        verify(productRepository, atLeastOnce()).save(createProduct);

    }

    @Test
    @DisplayName("service: updateProduct")
    public void givenProduct_whenUpdateProductCalled_thenProductIsUpdates() {
        Product createProduct = createProducts();

        //given
        Product updateProduct = new Product(80L, "Updated Product", "Updated Category");
        when(productRepository.findById(80L)).thenReturn(Optional.of(createProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updateProduct);

        //when

        Product updatedProduct = productService.updateProduct(80L, updateProduct);

        //then
        assertNotNull(updatedProduct);
        assertEquals(80L, updatedProduct.getId());
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals("Updated Category", updatedProduct.getCategory());

        verify(productRepository, atLeastOnce()).findById(80L);
        verify(productRepository, atLeastOnce()).save(any(Product.class));


    }

    @Test
    @DisplayName("service: findProductById")
    public void givenProductId_whenFindProductByIdCalled_thenProductIsReturn() {

        Product createProduct = createProducts();
        //given
        when(productRepository.findById(80L)).thenReturn(Optional.of(createProduct));

        //when
        Product retrieveProduct = productService.findProductById(80L);

        //then
        assertNotNull(retrieveProduct);
        assertEquals(80L, retrieveProduct.getId());
        assertEquals("Audi", retrieveProduct.getName());
        assertEquals("Vehicle", retrieveProduct.getCategory());

        verify(productRepository, atLeastOnce()).findById(80L);

    }

    @Test
    @DisplayName("service: findAllProducts")
    public void whenFindAllProductsCalled_thenAllProductIsReturn() {

        Product createProduct = createProducts();
        Product product2 = new Product(3L, "Product 3", "Category 3");
        List<Product> productList = Arrays.asList(createProduct, product2);

        //given
        when(productRepository.findAll()).thenReturn(productList);
        //when
        List<Product> allProducts = productService.findAllProducts();
        //then
        assertNotNull(allProducts);

        assertEquals(2, allProducts.size());
        verify(productRepository, times(1)).findAll();

    }


    @Test
    void givenProductId_whenDeleteOrderIsCalled_thenOrderIsDeletedSuccessfully() {
        Long productId = createProducts().getId();

        // Given
        doNothing().when(productRepository).deleteById(productId);

        // When
        String result = productService.DeleteOrder(productId);

        // Then
        assertEquals("Record Deleted", result);
        verify(productRepository, times(1)).deleteById(productId);
    }
}


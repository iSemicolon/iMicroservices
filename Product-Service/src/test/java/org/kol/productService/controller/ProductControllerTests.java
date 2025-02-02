package org.kol.productService.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kol.productService.entity.Product;
import org.kol.productService.service.ProductService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.kol.productService.utils.ProductUtils.createProducts;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test()
    @DisplayName("controller: addProduct")
    public void givenValidProduct_whenCreateProductIsCalled_thenProductIsCreatedSuccessfully() throws Exception {

        Product createProduct = createProducts();
        //given
        when(productService.saveProduct(any(Product.class))).thenReturn(createProduct);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/product/create-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(80L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Audi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Vehicle"));

        verify(productService, times(1)).saveProduct(any(Product.class));

    }

    @Test()
    @DisplayName("controller: updateProduct")
    public void givenValidProduct_whenUpdateProductIsCalled_thenProductIsUpdatedSuccessfully() throws Exception {

        Product createProduct = createProducts();
        //given
        when(productService.updateProduct(eq(createProduct.getId()), any(Product.class))).thenReturn(createProduct);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/product/update-product/80")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(80L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Audi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Vehicle"));

        verify(productService, times(1)).updateProduct(eq(createProduct.getId()), any(Product.class));

    }


    @Test()
    @DisplayName("controller: fetchProductById")
    public void givenValidProductId_whenGetProductByIdIsCalled_thenFetchProductIsSuccessfully() throws Exception {

        Product createProduct = createProducts();
        //given
        when(productService.findProductById(eq(80L))).thenReturn(createProduct);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/fetch-product-by-id/80")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(80L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Audi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Vehicle"));

        verify(productService, times(1)).findProductById(80L);
    }


    @Test()
    @DisplayName("controller: getAllProduct")
    public void whenGetAllProductIsCalled_thenFetchAllProductIsSuccessfully() throws Exception {

        Product createProduct = createProducts();
        // Given
        List<Product> productList = Arrays.asList(createProduct, new Product(2L, "Another Product", "Another Category"));
        when(productService.findAllProducts()).thenReturn(productList);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/product/fetch-all-products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(80L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Audi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category").value("Vehicle"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Another Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category").value("Another Category"));

        verify(productService, times(1)).findAllProducts();
    }


    @Test()
    @DisplayName("controller: deleteProductById")
    public void whenDeleteProductByIdIsCalled_thenDeleteOrderIsSuccessfully() throws Exception {

        Product createProduct = createProducts();

        String expectedMessage = "Product deleted successfully";
        // Given
        when(productService.DeleteOrder(createProduct.getId())).thenReturn(expectedMessage);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/remove-product-by-id/{Id}", createProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedMessage));


        verify(productService, times(1)).DeleteOrder(createProduct.getId());
    }
}

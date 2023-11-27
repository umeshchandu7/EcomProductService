package com.scaler.EcoProductService.controller;

import com.scaler.EcoProductService.dto.ProductListResponseDTO;
import com.scaler.EcoProductService.dto.ProductRequestDTO;
import com.scaler.EcoProductService.dto.ProductResponseDTO;
import com.scaler.EcoProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products/{id}")
    public ResponseEntity getProductFromId(@PathVariable("id") int id)
    {
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/products")
    public ResponseEntity getAllProducts()
    {
        ProductListResponseDTO responseDTO = productService.getAllProducts();
        return ResponseEntity.ok(responseDTO);
    }
  @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO)
  {
      ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
      return ResponseEntity.ok(productResponseDTO);
  }
  @DeleteMapping("/products/{id}")
  public void deleteProductById(@PathVariable("id") int id)
  {
      productService.deleteProduct(id);
  }

}

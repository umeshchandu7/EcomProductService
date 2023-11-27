package com.scaler.EcoProductService.service;

import com.scaler.EcoProductService.dto.ProductListResponseDTO;
import com.scaler.EcoProductService.dto.ProductRequestDTO;
import com.scaler.EcoProductService.dto.ProductResponseDTO;
import com.scaler.EcoProductService.exception.ProductNotFoundException;

public interface ProductService {
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException;
    public ProductListResponseDTO getAllProducts();
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    public boolean deleteProduct(int id);

}


package com.scaler.EcoProductService.service;

import com.scaler.EcoProductService.client.FakeStoreAPIClient;
import com.scaler.EcoProductService.dto.*;
import com.scaler.EcoProductService.exception.ProductNotFoundException;
import com.scaler.EcoProductService.mapper.ProductMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.scaler.EcoProductService.mapper.ProductMapper.fakeStoreResponseToProductResponse;
import static com.scaler.EcoProductService.mapper.ProductMapper.productRequestToFakeStoreRequest;
import static com.scaler.EcoProductService.util.ProductUtils.isNull;
@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private FakeStoreAPIClient fakeStoreAPIClient;
    private RestTemplateBuilder restTemplateBuilder;


    @Override
    public boolean deleteProduct(int id) {
           fakeStoreAPIClient.deleteProduct(id);
           return true;
    }

    public FakeStoreProductServiceImpl(FakeStoreAPIClient fakeStoreAPIClient, RestTemplateBuilder restTemplateBuilder) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = ProductMapper.productRequestToFakeStoreRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);
        return fakeStoreResponseToProductResponse(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
      List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOList = fakeStoreAPIClient.getAllProducts();
      ProductListResponseDTO productListResponseDTO = new ProductListResponseDTO();
      for(FakeStoreProductResponseDTO fakeStoreProductResponseDTO:fakeStoreProductResponseDTOList)
      {
          ProductResponseDTO productResponseDTO = fakeStoreResponseToProductResponse(fakeStoreProductResponseDTO);
          productListResponseDTO.getProducts().add(productResponseDTO);
      }
      return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
        if(isNull(fakeStoreProductResponseDTO))
        {
            throw new ProductNotFoundException("Product not found with id : "+id);
        }
        return fakeStoreResponseToProductResponse(fakeStoreProductResponseDTO);
    }

}

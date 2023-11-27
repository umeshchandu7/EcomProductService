package com.scaler.EcoProductService.client;

import com.scaler.EcoProductService.dto.FakeStoreProductRequestDTO;
import com.scaler.EcoProductService.dto.FakeStoreProductResponseDTO;
import com.scaler.EcoProductService.dto.ProductRequestDTO;
import com.scaler.EcoProductService.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest;

import java.net.URL;
import java.util.List;

@Component
public class FakeStoreAPIClient {
    public FakeStoreAPIClient(@Value("${Fakestore.path.url}")String fakeStoreUrl,@Value("${Fakestore.path.product}") String fakeStorePath, RestTemplateBuilder restTemplateBuilder) {
        this.fakeStoreUrl = fakeStoreUrl;
        this.fakeStorePath = fakeStorePath;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private String fakeStoreUrl;
    private String fakeStorePath;
    private RestTemplateBuilder restTemplateBuilder;



    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO fakeStoreProductRequestDTO)
    {
           String createURL = fakeStoreUrl+fakeStorePath;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.postForEntity(createURL,fakeStoreProductRequestDTO,FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }
    public FakeStoreProductResponseDTO getProductById(int id)
    {
        String getProductByIdURL = fakeStoreUrl+fakeStorePath+"/"+id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse = restTemplate.getForEntity(getProductByIdURL,FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }
    public List<FakeStoreProductResponseDTO> getAllProducts()
    {
        String getAllProductsURL = fakeStoreUrl+fakeStorePath;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseArray = restTemplate.getForEntity(getAllProductsURL,FakeStoreProductResponseDTO[].class);
        return List.of(productResponseArray.getBody());
    }
    public void deleteProduct(int id)
    {
        String productDeleteURL = fakeStoreUrl+fakeStorePath+"/"+id;
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(productDeleteURL);
    }


}

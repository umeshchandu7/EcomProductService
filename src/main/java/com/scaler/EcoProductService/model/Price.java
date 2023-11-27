package com.scaler.EcoProductService.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "PRICE")
public class Price extends BaseModel{
    private String currency;
    private double price;
    private double discount;
}

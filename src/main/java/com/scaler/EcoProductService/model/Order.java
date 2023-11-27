package com.scaler.EcoProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity(name = "ECO_ORDER")
public class Order extends BaseModel{
    @ManyToMany
    private List<Product> product;
}

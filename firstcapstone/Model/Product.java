package com.example.firstcapstone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "Product ID  must not be empty")
    private String ID;
    @NotNull(message = "Product Name  must not be empty")
    @Size(min = 3, message = "Product Name have to be more than 3 length long")
    private String name;
    @Positive(message = "Product Price must be Positive")
    @Min(value = 1, message = "Product Price must be at least 1 SR")
    private double price;
    @NotNull(message = "Product Category name  must not be empty")
    private String categoryID;
    @NotNull(message = "Sale must not be empty")
    @Min(value = 0,message = "Sale At least 0")
    private int sales;

}

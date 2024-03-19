package com.example.firstcapstone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "Merchant Stock ID  must not be empty")
    private String ID;
    @NotNull(message = "Productid must not be empty")
    private Product productid;
    @NotNull(message = "merchantid must not be empty")
    private Merchant merchantid;
    @NotNull(message = "Stock must not be empty ")
    @Positive(message = "Stock must be positive")
    @Min(value = 10,message = "have to be more than 10 at start")
    private Integer stock;
}

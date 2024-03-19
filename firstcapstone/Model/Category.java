package com.example.firstcapstone.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Category {
    @NotNull(message = "Category ID  must not be empty")
    private String ID;
    @NotNull(message = "Category Name  must not be empty")
    @Size(min = 3,message = "Category Name have to be more than 3 length long")
    private String name;
}

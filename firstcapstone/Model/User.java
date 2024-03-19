package com.example.firstcapstone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data@AllArgsConstructor
public class User {
//• id (must not be empty).
//• username (must not be empty, have to be more than 5 length long).
//• password (must not be empty, have to be more than 6 length long, must have
//characters and digits).
//    • email (must not be empty, must be valid email).
//    • role (must not be empty, have to be in ( “Admin”,”Customer”)).
//     • balance (must not be empty, have to be positive).
    @NotNull(message ="User ID must not be empty" )
    private String ID;
    @NotNull(message = "username must not be empty")
    @Size(min = 5,message = "username have to be more than 5 length long")
    private String username;
    @NotNull(message = "password must not be empty")
    @Size(min = 6,message = "password have to be more than 6 length long")
    //@Pattern(regexp = "^[ A-Za-z0-9_@./#&+-]*$")
    private String password;
    @Email(message = "must be valid email")
    @NotNull(message = "Email must not be empty")
    private String email;
    @Pattern(regexp = "Admin|Customer")
    private String role;
    @NotNull(message ="Balance must not be empty" )
    @Positive(message = "Balance must be positive")
    private double balance;
//{
//"ID":"123",
//"username":"Ghaliah","password":"Ghaliah$@123","email":"Ghaliah$@123.com","role":"Admin","balance":2000
//
//
//}


}

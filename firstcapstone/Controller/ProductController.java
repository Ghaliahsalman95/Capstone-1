package com.example.firstcapstone.Controller;

import com.example.firstcapstone.APIResponse.APIResponse;
import com.example.firstcapstone.Model.Merchant;
import com.example.firstcapstone.Model.Product;
import com.example.firstcapstone.Model.User;
import com.example.firstcapstone.Service.ProductService;
import com.example.firstcapstone.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;



    @GetMapping("/get-products")
    public ResponseEntity getproduct(){
        if (productService.getProducts().isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Product Empty"));
        else  return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @PostMapping("/add-product/{adminID}/{merchantStockID}/{merchantID}/{categoryID}")
    public ResponseEntity addproduct(@PathVariable String categoryID,@PathVariable String merchantStockID,@PathVariable String adminID, @RequestBody @Valid Product product, @PathVariable String merchantID, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if(productService.addProduct(adminID,product,merchantID,merchantStockID,categoryID))
        { return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Product "+product.getID()+" Added successfully"));}
        return ResponseEntity.status(400).body(new APIResponse("Add Product allow for admin user"));
    }

    @PutMapping("/update-product/{productID}/{adminID}/{merchantID}/{merchantStockID}")
    public ResponseEntity updateproduct(@PathVariable String merchantStockID,@PathVariable String merchantID,@PathVariable String productID,@PathVariable String adminID,@RequestBody@Valid Product product, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if(productService.update(adminID,productID,product,merchantID,merchantStockID)==1)
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Product "+product.getName()+" Updated successfully"));
        else if(productService.update(adminID,productID,product,merchantID,merchantStockID)==-1)
            return ResponseEntity.status(400).body(new APIResponse("Product ID"+productID+" Not Found"));
        return ResponseEntity.status(400).body(new APIResponse("Update Product allow for admin user"));
    }

    @DeleteMapping("/delete-product/{productID}/{adminID}/{merchantStockID}/{merchantID}")
    public ResponseEntity deleteProduct(@PathVariable String merchantID,@PathVariable String merchantStockID,@PathVariable String adminID,@PathVariable String productID){
        if(productService.delete(adminID,productID,merchantStockID,merchantID)==1)
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Product "+productID+" Deleted successfully"));
        else if(productService.delete(adminID,productID,merchantStockID,merchantID)==-1) return ResponseEntity.status(400).body(new APIResponse("Product ID"+productID+" Not Found"));
        return ResponseEntity.status(400).body(new APIResponse("Deleted Product allow for admin user"));
    }
    @PutMapping("/buy/{customerID}/{productID}/{merchantID}")
    public ResponseEntity buy(@PathVariable String customerID,@PathVariable String productID,@PathVariable String merchantID){
        if (productService.buyProduct(customerID,productID,merchantID)==1)
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Buying Successfully product ID"+productID+"From Merchant ID"+merchantID));

        if (productService.buyProduct(customerID,productID,merchantID)==-1)
            return ResponseEntity.status(400).body(new APIResponse("Out of stock"));
        return ResponseEntity.status(400).body(new APIResponse("Not Found MerchantID"));
    }
    //String productID, String merchantID, String merchantStockID, String userID,int stock
    @PutMapping("/return-product/{productID}/{merchantID}/{merchantStockID}/{userID}/{stock}")
    public ResponseEntity returnProduct(@PathVariable String productID,@PathVariable String merchantID,@PathVariable String merchantStockID,@PathVariable String userID,@PathVariable int stock){

        if (productService.productRetrn(productID,merchantID,merchantStockID,userID,stock))
        {return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("The Product "+productID+" was successfully returned by the customer "+userID+" with stock "+stock));}

        return ResponseEntity.status(400).body(new APIResponse("User ID"+userID+" Not found"));
    }
    @GetMapping("/get-top-sale/{num}")
    public ResponseEntity getTop(@PathVariable int num){
        if (productService.topSales(num).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("There is no top sale now"));
        return ResponseEntity.status(HttpStatus.OK).body(productService.topSales(num));
    }



    @GetMapping("/productFilter/{categoryName}")
    public ResponseEntity getProductByCategory(@PathVariable String categoryName){
        if (productService.filter(categoryName).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("There is no product from category"+categoryName));
        return ResponseEntity.status(HttpStatus.OK).body(productService.filter(categoryName));
    }
}

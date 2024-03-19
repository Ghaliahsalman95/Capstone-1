package com.example.firstcapstone.Controller;

import com.example.firstcapstone.APIResponse.APIResponse;
import com.example.firstcapstone.Model.Merchant;
import com.example.firstcapstone.Model.MerchantStock;
import com.example.firstcapstone.Service.MerchantStockService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {



    private final MerchantStockService merchantStockService;

    @GetMapping("/get-merchantStock")
    public ResponseEntity getMerchantStock(){
        if (merchantStockService.getMerchantStocks().isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Merchant Stocks Empty"));
        else  return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add-merchantStock")
    public ResponseEntity addMerchantstock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (merchantStockService.addMerchantStock(merchantStock))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("MerchantStocks "+merchantStock.getID()+" Added successfully"));
        else return ResponseEntity.status(400).body(new APIResponse(""));

    }

    @PutMapping("/update-merchantStock/{merchantStockID}")
    public ResponseEntity updateMerchant(@PathVariable String merchantStockID,@RequestBody@Valid MerchantStock merchantStock, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if(merchantStockService.update(merchantStockID,merchantStock))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("MerchantStocks "+merchantStock.getID()+" Updated successfully"));
        else return ResponseEntity.status(400).body(new APIResponse("MerchantStocks ID"+merchantStockID+" Not Found"));
    }

    @DeleteMapping("/delete-merchantStock/{merchantStockID}")
    public ResponseEntity deletemerchant(@PathVariable String merchantStockID){
        if(merchantStockService.delete(merchantStockID))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("MerchantStock "+merchantStockID+" Deleted successfully"));
        else return ResponseEntity.status(400).body(new APIResponse("MerchantStock ID"+merchantStockID+" Not Found"));
    }
@PutMapping("/add-stock/{productID}/{merchant}/{stock}")
public ResponseEntity addStock(@PathVariable String productID,@PathVariable String merchant,@PathVariable int stock)
{       if (merchantStockService.addStocks(stock,productID,merchant))
    return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Merchant ID "+merchant+" Re Stock for product ID"+ productID+" successfully"));
    return ResponseEntity.status(400).body(new APIResponse("Not found IDs "));

}
    @GetMapping("/get-out-stock/{productID}")
    public ResponseEntity outStock(@PathVariable String productID){
        if (merchantStockService.getOutOfStock(productID).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Stocks greater than 0"));
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getOutOfStock(productID));
    }

    @GetMapping("/get-almost-out-stock/{productID}")
    public ResponseEntity almoustoutStock(@PathVariable String productID){
        if (merchantStockService.almostOutOfStock(productID).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Stocks greater than 3"));
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.almostOutOfStock(productID));
    }
    @GetMapping("/get-no-sale/{merchantID}")
    public ResponseEntity getproductNosale(@PathVariable String merchantID) {
        if(merchantStockService.nosale(merchantID).isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("no product ( no sale )"));
return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.nosale(merchantID));
    }


}

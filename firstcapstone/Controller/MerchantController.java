package com.example.firstcapstone.Controller;

import com.example.firstcapstone.APIResponse.APIResponse;
import com.example.firstcapstone.Model.Category;
import com.example.firstcapstone.Model.Merchant;
import com.example.firstcapstone.Model.MerchantStock;
import com.example.firstcapstone.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get-merchant")
    public ResponseEntity getMerchants(){
        if (merchantService.getMerchants().isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("Merchants Empty"));
        else  return ResponseEntity.status(HttpStatus.OK).body(merchantService.getMerchants());
    }

    @PostMapping("/add-merchant")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Merchant "+merchant.getName()+" Added successfully"));
    }

    @PutMapping("/update-merchant/{merchantID}")
    public ResponseEntity updateMerchant(@PathVariable String merchantID,@RequestBody@Valid Merchant merchant, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if(merchantService.updateMerchant(merchantID,merchant))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Merchant "+merchant.getName()+" Updated successfully"));
        else return ResponseEntity.status(400).body(new APIResponse("Merchant ID"+merchantID+" Not Found"));
    }

    @DeleteMapping("/delete-merchant/{merchantID}")
    public ResponseEntity deletemerchant(@PathVariable String merchantID){
        if(merchantService.deleteMerchant(merchantID))
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Merchant "+merchantID+" Deleted successfully"));
        else return ResponseEntity.status(400).body(new APIResponse("Merchant ID"+merchantID+" Not Found"));
    }




}

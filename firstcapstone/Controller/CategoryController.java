package com.example.firstcapstone.Controller;

import com.example.firstcapstone.APIResponse.APIResponse;
import com.example.firstcapstone.Model.Category;
import com.example.firstcapstone.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    @GetMapping("/get-category")
    public ResponseEntity getCategories(){
if (categoryService.getCategories().isEmpty())
    return ResponseEntity.status(400).body(new APIResponse("Category Empty"));
    else  return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    @PostMapping("/add-category/{adminID}")
    public ResponseEntity addCategory(@PathVariable String adminID,@RequestBody@Valid Category category, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if(categoryService.addCategory(adminID,category))           return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Category "+category.getName()+" Added successfully"));
        return ResponseEntity.status(400).body(new APIResponse("Add Category Allow for Admin"));
    }

    @PutMapping("/update-category/{categoryID}/{adminID}")
    public ResponseEntity updateCategory(@PathVariable String  adminID,@PathVariable String categoryID,@RequestBody@Valid Category category, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if(categoryService.updateCategory(adminID,categoryID,category)==1)
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Category "+category.getName()+" Updated successfully"));
        else if(categoryService.updateCategory(adminID,categoryID,category)==-1)return ResponseEntity.status(400).body(new APIResponse("Category ID"+categoryID+" Not Found"));
        return ResponseEntity.status(400).body(new APIResponse("Update Category Allow for Admin"));

    }

    @DeleteMapping("/delete-category/{categoryID}/{adminID}")
    public ResponseEntity deleteCategory(@PathVariable String adminID,@PathVariable String categoryID){
      if(categoryService.deleteCategory(adminID,categoryID)==1)
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Category "+categoryID+" Deleted successfully"));
        else if(categoryService.deleteCategory(adminID,categoryID)==1)return ResponseEntity.status(400).body(new APIResponse("Category ID"+categoryID+" Not Found"));
        return ResponseEntity.status(400).body(new APIResponse("Delete Category Allow for Admin"));

    }

}

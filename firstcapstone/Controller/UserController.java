package com.example.firstcapstone.Controller;

import com.example.firstcapstone.APIResponse.APIResponse;
import com.example.firstcapstone.Model.Category;
import com.example.firstcapstone.Model.User;
import com.example.firstcapstone.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity getUsers(){
        if (userService.getUsers().isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("User Empty"));
        else  return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User "+user.getID()+" Added successfully"));
    }

    @PutMapping("/update-user/{userID}/{Admin}")
    public ResponseEntity updateUser(@PathVariable String Admin,@PathVariable String userID,@RequestBody@Valid User user, Errors errors){
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if(userService.update(Admin,userID,user)==1)
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User "+user.getUsername()+" Updated successfully"));
        else  if(userService.update(Admin,userID,user)==-1)return ResponseEntity.status(400).body(new APIResponse("User ID"+userID+" Not Found"));
        return ResponseEntity.status(400).body(new APIResponse("Update user allow for admin user"));

    }

    @DeleteMapping("/delete-user/{userID}/{Admin}")
    public ResponseEntity deleteUser(@PathVariable String Admin,@PathVariable String userID){
        if(userService.delete(Admin,userID)==1)
        {return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User "+userID+" Deleted successfully"));}
        else if(userService.delete(Admin,userID)==-1)
        { return ResponseEntity.status(400).body(new APIResponse("User ID"+userID+" Not Found"));}
        return ResponseEntity.status(400).body(new APIResponse("Delete user allow for admin user"));
    }


}

package com.example.firstcapstone.Service;

import com.example.firstcapstone.Model.MerchantStock;
import com.example.firstcapstone.Model.Product;
import com.example.firstcapstone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@RequiredArgsConstructor
@Service
public class UserService  {


    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public int update(String admin, String ID, User user) {
        if (IsAdmin(admin)) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getID().equalsIgnoreCase(ID)) {
                        users.set(i, user);
                        return 1;
                    }

                }return -1;
        }
        return 0;
    }

    public int delete(String Admin, String ID) {

        if (IsAdmin(Admin)) {
            for (User user : users) {
                if (user.getID().equalsIgnoreCase(ID))
                    users.remove(user);
                return 1;
            }
            return -1;
        }

        return 0;
    }

    //-------------------------------

    //------------------------------------------------------------------------------------------
    //12- Create endpoint where user can buy a product directly
    //• this endpoint should accept user id, product id, merchant id.
    //• check if all the given ids are valid or not
    //• check if the merchant has the product in stock or return bad request.
    //• reduce the stock from the MerchantStock.
    //• deducted the price of the product from the user balance.
    //• if balance is less than the product price return bad request.



    //
    public boolean IsAdmin(String ID) {
        for (User user : users) {
            if (user.getID().equalsIgnoreCase(ID) && user.getRole().equalsIgnoreCase("Admin"))
                return true;
        }
        return false;
    }
    public User getUser(String ID) {
        for (User user : users) {
            if (user.getID().equalsIgnoreCase(ID)){
                return user;
            }
        }
        return null;
    }



}
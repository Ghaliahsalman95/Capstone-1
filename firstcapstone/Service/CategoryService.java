package com.example.firstcapstone.Service;

import com.example.firstcapstone.Model.Category;
import com.example.firstcapstone.Model.MerchantStock;
import com.example.firstcapstone.Model.Product;
import com.example.firstcapstone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private  final UserService userService;
    //private final ProductService productService;
    ArrayList<Category> categories = new ArrayList<>();


    //Create endpoint for getting and adding and deleting updating a
    //Category.
    public ArrayList<Category> getCategories() {

        return categories;
    }

    public boolean addCategory(String admin, Category category) {
if(userService.IsAdmin(admin))
{categories.add(category);return true;}
        return false;
    }

    public int updateCategory(String admin, String ID, Category category) {

            if (userService.IsAdmin(admin)) {
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getID().equalsIgnoreCase(ID)) {
                        categories.set(i, category);
                        return 1;
                    }
                }
                return -1;
            }
        return 0;  }


    //-------------------------------------------------
    public int  deleteCategory(String adminID,String categoryID) {
        if(userService.IsAdmin(adminID)){
        for (Category category1 : categories) {
            if (category1.getID().equalsIgnoreCase(categoryID)) {
                categories.remove(category1);
                return 1;
            }
        }
        return -1;
    }return 0;}

    public Category getOneCat(String Id){
        for (Category category:categories){
            if(category.getID().equalsIgnoreCase(Id))
                return category;}
    return null;}
//----------------------------------------

}

package com.example.firstcapstone.Service;

import com.example.firstcapstone.Model.MerchantStock;
import com.example.firstcapstone.Model.Product;
import com.example.firstcapstone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//import static sun.security.x509.PolicyInformation.ID;

@Service
@RequiredArgsConstructor
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantStockService merchantStockService;
    @Autowired
    private CategoryService categoryService;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean addProduct(String admin, Product product) {
        if (userService.IsAdmin(admin)) {

            if (categoryService.getOneCat(product.getCategoryID()) != null) {
                products.add(product);
                return true;
//
            }
            return false;
        }
        return false;
    }

    public int update(String admin, String productID, Product product) {
        if (userService.IsAdmin(admin)&&categoryService.getOneCat(product.getCategoryID())!=null) {

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getID().equalsIgnoreCase(productID)) {
                    products.set(i, product);
                    return 1;
                }

                return -1;
            }
            return -2;
        }
        return 0;
    }

    public int delete(String Admin, String proID) {
        if (userService.IsAdmin(Admin)) {
            for (Product product : products) {
                if (product.getID().equalsIgnoreCase(proID)) {
                    products.remove(product);
                    return 1;
                }
            }
            return -1;
        }


        return 0;
    }

    public int buyProduct(String userid, String productid, String merchantid) {
        if (!userService.IsAdmin(userid)) {
            for (MerchantStock merchantStock : merchantStockService.getMerchantStocks()) {
                if (merchantStock.getMerchantid().equalsIgnoreCase(merchantid) &&
                        merchantStock.getProductid().equalsIgnoreCase(productid)) {
                    if (merchantStock.getStock() > 0) {
                        if (userService.getUser(userid).getBalance() > getPRoduct(productid).getPrice()) {
                            merchantStock.setStock(merchantStock.getStock() - 1);
                            userService.getUser(userid).
                                    setBalance(userService.getUser(userid).getBalance() - getPRoduct(productid).getPrice());
                            Product product = getPRoduct(productid);
                            product.setSales(getPRoduct(productid).getSales() + 1);//for extra credit TOP SALE;
                            return 1;
                        }

                    }
                    return -1;

                }
            }
        }


        return 0;
    }

    public Product getPRoduct(String id) {
        for (Product product : products) {
            if (product.getID().equalsIgnoreCase(id))
                return product;
        }
        return null;
    }

    /////////////////////////////////1-TOP SALES Extra Credit
    public ArrayList<Product> topSales(int num) {//num of retrieve product or size arraylist
        ArrayList<Product> topsaleList = new ArrayList<>();
        int sale = 5;//assume top sale is 5 becuase i can NOT test when buy 100 times
        for (Product product : products) {
            if (product.getSales() > sale) {
                sale = product.getSales();
                topsaleList.add(product);
            }
        }
        if (num < topsaleList.size()) {
            ArrayList<Product> topNum = new ArrayList<>();
            for (int p = 0; p < num; p++) {
                topNum.add(topsaleList.get(p));
            }
            return topNum; // just num Products
        } else return topsaleList;
    }

    //2-Extra return Product stock
    public boolean productRetrn(String productID, String userID, int stock) {
        if (!userService.IsAdmin(userID)) {//id for customer
//
            for(int i=0; i<merchantStockService.merchantStocks.size();i++){
            User user = userService.getUser(userID);
            user.setBalance(user.getBalance() + getPRoduct(productID).getPrice());// return money
                if(merchantStockService.merchantStocks.get(i).getProductid().equalsIgnoreCase(productID)){
                    MerchantStock merchantStock1=new MerchantStock(merchantStockService.merchantStocks.get(i).getID(),productID,merchantStockService.merchantStocks.get(i).getMerchantid(),merchantStockService.merchantStocks.get(i).getStock()+stock);
                    merchantStockService.merchantStocks.set(i,merchantStock1);
                }
            Product product = getPRoduct(productID);
            int sale = product.getSales() - stock;
            product.setSales(sale);
            for (int ii = 0; ii < products.size(); ii++) {
                if (product.getID().equalsIgnoreCase(products.get(ii).getID())) {
                    products.set(ii, product);
                }
            }


            return true;
        }
        return false;
    }
        return false;
}


////////////////////////////

//-------------------------3 extra credit Filter ---------------
public ArrayList<Product> filter(String category) {
    ArrayList<Product> filterList = new ArrayList<>();
    for (Product product : products) {
        if (product.getCategoryID().equalsIgnoreCase(category))
            filterList.add(product);
    }
    return filterList;
}


/////////////////


}

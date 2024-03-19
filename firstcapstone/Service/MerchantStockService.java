package com.example.firstcapstone.Service;

import com.example.firstcapstone.Model.Merchant;
import com.example.firstcapstone.Model.MerchantStock;
import com.example.firstcapstone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final MerchantService merchantService;
    @Autowired
    private ProductService productService;

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock) {

            merchantStocks.add(merchantStock);return true;}

    public boolean update(String ID, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getID().equalsIgnoreCase(ID)) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String ID) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getID().equalsIgnoreCase(ID)) {
                merchantStocks.remove(merchantStock);
                return true;
            }
        }
        return false;

    }

    public boolean addStocks(int stocks, String productID, String merchantID) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getMerchantid().getID().equalsIgnoreCase(merchantID)&&merchantStock.getProductid().getID().equalsIgnoreCase(productID)) {
                    merchantStock.setStock(merchantStock.getStock() + stocks);
                    return true;
                }
            }return false;


    }

    //
    public boolean isOwner(String merchantID, String productID) {
     for(MerchantStock merchantStock:merchantStocks){
            if (merchantStock.getMerchantid().getID().equalsIgnoreCase(merchantID) && merchantStock.getProductid().getID().equalsIgnoreCase(productID))
            {return true;}
        }
        return false;
    }
    public MerchantStock getMerchantstock(String id){
        for (MerchantStock merchantStock:merchantStocks){
            if(merchantStock.getID().equalsIgnoreCase(id))
                return merchantStock;
        }return null;
    }
/////////////////////////////////4-Extra Out of stock

    public ArrayList<MerchantStock>getOutOfStock(String proID){
        ArrayList<MerchantStock>outStock=new ArrayList<>();
        for (MerchantStock merchantStock:merchantStocks){
            if (merchantStock.getProductid().getID().equalsIgnoreCase(proID)&&merchantStock.getStock()==0)
                outStock.add(merchantStock);
        }return outStock;
    }
    /////////////////////////////////5- Extra almost out of stock

    public ArrayList<MerchantStock>almostOutOfStock(String proID){
        ArrayList<MerchantStock>almostoutStock=new ArrayList<>();
        for (MerchantStock merchantStock:merchantStocks){
            if (merchantStock.getProductid().getID().equalsIgnoreCase(proID)&&merchantStock.getStock()>=3)
                almostoutStock.add(merchantStock);
        }return almostoutStock;
    }

    /////////////

    public ArrayList<MerchantStock> nosale(String merchantID){
        ArrayList<MerchantStock>nosaleList=new ArrayList<>();
        for (MerchantStock merchantStock:merchantStocks){
                if (merchantStock.getMerchantid().getID().equalsIgnoreCase(merchantID)){
                        if (merchantStock.getProductid().getSales()==0){
                                nosaleList.add(merchantStock);
                            }
                        }
                    }
    return nosaleList;}
}
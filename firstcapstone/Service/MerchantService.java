package com.example.firstcapstone.Service;

import com.example.firstcapstone.Model.Category;
import com.example.firstcapstone.Model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class MerchantService {

ArrayList<Merchant>merchants=new ArrayList<>();
    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }
    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }
    public boolean updateMerchant(String ID,Merchant merchant){
        for (int i=0;i<merchants.size();i++){
            if (merchants.get(i).getID().equalsIgnoreCase(ID)){
                merchants.set(i,merchant);
                return true;}}return false;}
    //-------------------------------------------------
    public boolean deleteMerchant(String merchantID){
        for (Merchant merchant1:merchants){
            if (merchant1.getID().equalsIgnoreCase(merchantID)){
                merchants.remove(merchant1);
            return true;}
        }return false;
    }
//----------------------

public Merchant getOne(String id){
        for (Merchant merchant:merchants){
            if (merchant.getID().equalsIgnoreCase(id))
                return merchant;
        }return null;
}




}

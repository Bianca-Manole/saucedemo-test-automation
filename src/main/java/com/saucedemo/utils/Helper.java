package com.saucedemo.utils;

import static testdata.URL.ITEM_DETAILS;

public class Helper {

    public static String getItemURL(String productID){
        return ITEM_DETAILS + productID;
    }
}

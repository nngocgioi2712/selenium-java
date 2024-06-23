package com.vmo.demowebshop.storedata;

import java.util.List;

public class AddToCartData {

    private static int numberAdded;
    private static List<String> prodsAdded;

    public static int getNumberAdded() {
        return numberAdded;
    }

    public static void setNumberAdded(int num) {
        numberAdded = num;
    }

    public static List<String> getProductsList() {
        return prodsAdded;
    }

    public static void setProductList(List<String> prods) {
        prodsAdded = prods;
    }
}
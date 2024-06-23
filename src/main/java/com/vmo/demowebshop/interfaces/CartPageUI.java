package com.vmo.demowebshop.interfaces;

import static com.vmo.demowebshop.common.Locator.xpath;

public class CartPageUI {

    public static final String PRODUCT_ITEMS = xpath("//tr[@class='cart-item-row']");
    public static final String REMOVE_CHECKBOX = xpath("td[@class='remove-from-cart']/input");
    public static final String PRODUCT_NAME = xpath("td[@class='product']/a");
    public static final String PRODUCT_QUANTITY = xpath("td[contains(@class,'qty')]/input");
    public static final String PRODUCT_TOTAL = xpath("td[contains(@class,'subtotal')]/span[@class='product-subtotal']");
    public static final String SUB_TOTAL_TXT = xpath("//span[text()='Sub-Total:']/ancestor::tr//span[@class='product-price']");


    public static final String UPDATE_CART_BTN = xpath("//input[@name='updatecart']");
    public static final String TERM_OF_SERVICE_CHECK = xpath("//input[@id='termsofservice']");
    public static final String CHECKOUT_BTN = xpath("//button[@id='checkout']");


}

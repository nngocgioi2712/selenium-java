package com.vmo.demowebshop.interfaces;

import static com.vmo.demowebshop.common.Locator.xpath;

public class HomePageUI {
    public static final String H_LINK_LOGIN_TAG = xpath("//div[contains(@class, 'header-links')]//a[text()='Log in']");
    public static final String H_LINK_SHOPPING_CART_TAG = xpath("//div[contains(@class, 'header-links')]//a/span[text()='Shopping cart']");
    public static final String SHOPPING_CART_ITEMS = xpath("//div[@class='mini-shopping-cart']//div[@class='name']/a");



    public static final String DYNAMIC_CAT_MENU = xpath("//div[contains(@class, 'category-navigation')]//li/a[contains(text(),'%s')]");
    //featured product
    public static final String FEATURED_PRODUCT_NAME = xpath("//div[@class='product-item']//h2[@class='product-title' and not(contains(.,'Virtual'))]");
}

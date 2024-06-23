package com.vmo.demowebshop.interfaces;

import static com.vmo.demowebshop.common.Locator.xpath;

public class CommonPageUI {

    //Header
    public static final String DYNAMIC_HEADER_LINK_TAG = xpath("//div[contains(@class, 'header-links')]//a[contains(.,'%s')]");
    public static final String SHOPPING_CART_ITEMS_TOTAL = xpath("//div[contains(@class, 'header-links')]//a/span[text()='Shopping cart']/following-sibling::span");
    public static final String DYNAMIC_HEADER_MENU_TAG = xpath("//ul[@class='top-menu']//a[contains(text(),'%s')]");
    public static final String MINI_SHOPPING_CART_ITEMS = xpath("//div[@class='mini-shopping-cart']//div[@class='name']/a");

    //Common
    public static final String PAGE_TITLE = xpath("//div[@class='page-title']/h1");
    public static final String LOADING_IMG = xpath("//div[@class='loading-image']");
    public static final String NOTIFICATION_MSG = xpath("//div[@id='bar-notification']//p");
    public static final String DYNAMIC_INPUT_BTN = xpath("//input[@value='%s']");

    public static final String PRODUCT_ADD_TO_CARD_BTN = xpath("//div[@class='details']//input[@value='Add to cart']");

    public static final String PRODUCT_NAME = xpath("td[@class='product']/a");


}

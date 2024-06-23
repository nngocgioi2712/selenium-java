package com.vmo.demowebshop.interfaces;

import static com.vmo.demowebshop.common.Locator.xpath;

public class BooksPageUI {

    public static final String ITEMS_HAS_ADD_TO_CART = xpath("//input[@value='Add to cart']//ancestor::div[@class='product-item']");
    public static final String RATINGS = xpath(".//div[@class='rating']/div[contains(@style, 'width')]");
    public static final String ADD_TO_CART_BTN = xpath(".//input[@value='Add to cart']");
    public static final String ITEM_NAME = xpath(".//h2[@class='product-title']");
}
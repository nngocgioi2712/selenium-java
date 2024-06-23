package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.CommonPageUI;
import com.vmo.demowebshop.storedata.AddToCartData;
import com.vmo.demowebshop.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeaderObject extends BasePage {
    private WebDriver driver;

    public HeaderObject(WebDriver driver) {
        this.driver = driver;
    }

    public HeaderObject verifyMessageIsDisplay(String message) {
        Log.allure("Verify: Message '%s' is displayed", message);
        softAssert.assertEquals(getTextElement(driver, CommonPageUI.NOTIFICATION_MSG), message);

        return this;
    }

    public HeaderObject verifyTotalItemsInShoppingCartIncreases(int number) {
        Log.allure("Verify: Total item in shopping cart increases %s", Integer.toString(number));

        int currentShoppingCartAmount = Integer.parseInt(getTextElement(driver, CommonPageUI.SHOPPING_CART_ITEMS_TOTAL).replaceAll("[^0-9]", ""));
        softAssert.assertEquals((currentShoppingCartAmount - AddToCartData.getNumberAdded()),number);

        return this;
    }

    public CartPageObject clickShoppingCartTag() {
        Log.allure("Step: Click Shopping cart tag");
        clickToElement(driver, CommonPageUI.DYNAMIC_HEADER_LINK_TAG, "Shopping cart");

        return new CartPageObject(driver);
    }

    public HeaderObject hoverToShoppingCart() {
        Log.allure("Step: Hover to Shopping cart");
        scrollToTopPage(driver);
        hoverMouseToElement(driver, CommonPageUI.DYNAMIC_HEADER_LINK_TAG, "Shopping cart");
        waitForElementVisible(driver, CommonPageUI.MINI_SHOPPING_CART_ITEMS);
        return this;
    }

    public HeaderObject verifyProductAddedInShoppingCart() {
        Log.allure("Verify: Products added are displayed in shopping cart");
        List<String> addedBookList = AddToCartData.getProductsList();
        List<String> actualBookInCart = new ArrayList<>();
        for (WebElement element: getListWebElements(driver, CommonPageUI.MINI_SHOPPING_CART_ITEMS)) {
            actualBookInCart.add(element.getText());

        }
        Collections.sort(actualBookInCart);
        Collections.sort(addedBookList);
        Assert.assertEquals(actualBookInCart, addedBookList);
        softAssert.assertAll();
        return this;
    }

}

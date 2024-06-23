package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.CommonPageUI;
import com.vmo.demowebshop.interfaces.HomePageUI;
import com.vmo.demowebshop.storedata.AddToCartData;
import com.vmo.demowebshop.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }
    public HomePageObject verifyInHomepage() {
        Log.allure("Verify: User in the Homepage");
        Assert.assertEquals(getTitle(driver), "Demo Web Shop");

        return this;
    }

    public LoginPageObject clickLoginTag() {
        Log.allure("Step: Click Login tag");
        clickToElement(driver, CommonPageUI.DYNAMIC_HEADER_LINK_TAG, "Log in");
        return new LoginPageObject(driver);
    }

    public BooksPageObject clickBooksMenu() {
        Log.allure("Step: Click Books menu");
        clickToElement(driver, CommonPageUI.DYNAMIC_HEADER_MENU_TAG, "Books");
        return new BooksPageObject(driver);
    }

    public DigitalDownloadsPageObject clickDigitalDownloadsMenu() {
        Log.allure("Step: Click Digital Downloads Menu");
        clickToElement(driver, CommonPageUI.DYNAMIC_HEADER_MENU_TAG, "Digital downloads");

        return new DigitalDownloadsPageObject(driver);
    }

    public HeaderObject addFeaturedItemsToShoppingCart(int number) {
        Log.allure("Step: Add %s Featured Items To Shopping Cart", Integer.toString(number));
        List<WebElement> items = getListWebElements(driver, HomePageUI.FEATURED_PRODUCT_NAME);
        if(items.size() < number) {
            throw new RuntimeException("The total items is not enough " + number);
        } else {
            AddToCartData.setNumberAdded(Integer.parseInt(getTextElement(driver, CommonPageUI.SHOPPING_CART_ITEMS_TOTAL).replaceAll("[^0-9]", "")));
            for (int i = 0; i < number; i++) {
                items.get(i).click();
                clickToElement(driver, CommonPageUI.DYNAMIC_INPUT_BTN, "Add to cart");
                backToPage(driver);
            }
        }
        refreshCurrentPage(driver);
        return new HeaderObject(driver);
    }



}

package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.CommonPageUI;
import com.vmo.demowebshop.storedata.AddToCartData;
import com.vmo.demowebshop.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DigitalDownloadsPageObject extends BasePage {
    private WebDriver driver;

    public DigitalDownloadsPageObject (WebDriver driver) {
        this.driver = driver;
    }

    public DigitalDownloadsPageObject verifyInDigitalDownloadsPage() {
        Log.allure("Verify: User in Digital Downloads page");
        waitForElementVisible(driver, CommonPageUI.PAGE_TITLE);
        Assert.assertEquals(getTextElement(driver, CommonPageUI.PAGE_TITLE), "Digital downloads");

        return this;
    }

    public HeaderObject clickAddToCartInAProduct() {
        Log.allure("Step: Click Add to cart in a product");
        AddToCartData.setNumberAdded(Integer.parseInt(getTextElement(driver, CommonPageUI.SHOPPING_CART_ITEMS_TOTAL).replaceAll("[^0-9]", "")));
        clickToElement(driver, CommonPageUI.PRODUCT_ADD_TO_CARD_BTN);
        waitForElementInvisible(driver, CommonPageUI.LOADING_IMG);

        return new HeaderObject(driver);
    }

}

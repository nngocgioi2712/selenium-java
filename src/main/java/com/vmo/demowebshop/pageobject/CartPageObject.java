package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.CartPageUI;
import com.vmo.demowebshop.interfaces.CommonPageUI;
import com.vmo.demowebshop.storedata.AddToCartData;
import com.vmo.demowebshop.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CartPageObject extends BasePage {
    private WebDriver driver;
    private String removedProductName;

    public CartPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public CartPageObject verifyInCartPage() {
        Log.allure("Verify: User in Shopping cart page");
        waitForElementVisible(driver, CommonPageUI.PAGE_TITLE);
        Assert.assertEquals(getTextElement(driver, CommonPageUI.PAGE_TITLE), "Shopping cart");

        //store productInCart
        List<WebElement> products = getListWebElements(driver, CartPageUI.PRODUCT_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement element : products) {
            productsName.add(getTextElement(element));
        }
        AddToCartData.setProductList(productsName);

        return this;
    }

    public CartPageObject chooseTheProductToBeRemove() {
        Log.allure("Step: Choose the product to be remove");
        WebElement product = getWebElement(driver, CartPageUI.PRODUCT_ITEMS);
        removedProductName = getTextElement(getWebElement(product, CartPageUI.PRODUCT_NAME));

        getWebElement(product, CartPageUI.REMOVE_CHECKBOX).click();

        return this;

    }

    public CartPageObject clickUpdateCartBtn() {
        Log.allure("Step: Click Update cart button");
        clickToElement(driver, CartPageUI.UPDATE_CART_BTN);

        return this;
    }

    public CartPageObject verifyItemRemoved() {
        Log.allure("Verify: Item is removed from shopping cart");
        boolean check = true;
        for (WebElement element : getListWebElements(driver, CartPageUI.PRODUCT_ITEMS)) {
            if (getTextElement(getWebElement(element, CartPageUI.PRODUCT_NAME)).equals(removedProductName)) {
                check = false;
                break;
            }
        }
        Assert.assertTrue(check);
        return this;
    }

    public CartPageObject verifySumOfTotalEqualToSubTotal() {
        List<WebElement> products = getListWebElements(driver, CartPageUI.PRODUCT_ITEMS);
        double subTotal =  Double.parseDouble(getTextElement(driver, CartPageUI.SUB_TOTAL_TXT));

        double sumOfTotal = 0.0;
        for(WebElement item : products) {
            int quantity = Integer.parseInt(getAttributeElement(getWebElement(item, CartPageUI.PRODUCT_QUANTITY), "value"));
            double total = Double.parseDouble(getTextElement(getWebElement(item, CartPageUI.PRODUCT_TOTAL)));
            sumOfTotal += quantity*total;
        }

        softAssert.assertEquals(sumOfTotal, subTotal);

        return this;
    }

    public CartPageObject checkToTermOfService() {
        checkToCheckboxOrRadio(driver,CartPageUI.TERM_OF_SERVICE_CHECK);

        return this;
    }

    public CartPageObject verifyCheckBoxIsChecked() {
        softAssert.assertTrue(isElementSelected(driver, CartPageUI.TERM_OF_SERVICE_CHECK));

        return this;
    }

    public CheckoutPageObject clickCheckoutButton() {
        clickToElement(driver, CartPageUI.CHECKOUT_BTN);

        return new CheckoutPageObject(driver);
    }

}

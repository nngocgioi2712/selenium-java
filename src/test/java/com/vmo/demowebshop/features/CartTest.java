package com.vmo.demowebshop.features;

import com.vmo.demowebshop.common.BaseTest;
import com.vmo.demowebshop.pageobject.HomePageObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("DemoWebShop")
@Feature("ShoppingCart")
public class CartTest extends BaseTest {

    @Test
    @Story("RemoveItemOutOfShoppingCart")
    public void TC_03_removeItemOutOfShoppingCart() {
        HomePageObject homePageObject = new HomePageObject(driver);

        homePageObject.verifyInHomepage()
                .addFeaturedItemsToShoppingCart(3)
                .verifyTotalItemsInShoppingCartIncreases(3)
                .clickShoppingCartTag()
                .verifyInCartPage()
                .chooseTheProductToBeRemove()
                .clickUpdateCartBtn()
                .verifyItemRemoved()
                .assertAll();
    }
}

package com.vmo.demowebshop.features;

import com.vmo.demowebshop.common.BaseTest;
import com.vmo.demowebshop.pageobject.HomePageObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("DemoWebShop")
@Feature("Books")
public class BooksTest extends BaseTest {

    @Test
    @Story("Buy two books highest rated")
    public void TC_02_buy2BooksHighestRate() {
        HomePageObject homePageObject = new HomePageObject(driver);

        homePageObject.verifyInHomepage()
                .clickBooksMenu()
                .verifyInBooksPage()
                .add2HighestRatingItems()
                .verifyMessageIsDisplay("The product has been added to your shopping cart")
                .verifyTotalItemsInShoppingCartIncreases(2)
                .hoverToShoppingCart()
                .verifyProductAddedInShoppingCart();
    }
}


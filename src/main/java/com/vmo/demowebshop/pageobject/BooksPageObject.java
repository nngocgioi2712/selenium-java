package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.BooksPageUI;
import com.vmo.demowebshop.interfaces.CommonPageUI;
import com.vmo.demowebshop.storedata.AddToCartData;
import com.vmo.demowebshop.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class BooksPageObject extends BasePage {
    private WebDriver driver;
    private List<String> addedBookList = new ArrayList<>();
    public BooksPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public BooksPageObject verifyInBooksPage() {
        Log.allure("Verify: User in Books page");
        softAssert.assertEquals(getTextElement(driver, CommonPageUI.PAGE_TITLE), "Books");

        return this;
    }

    public HeaderObject add2HighestRatingItems() {
        Log.allure("Step: Add 2 highest rating items");

        AddToCartData.setNumberAdded(Integer.parseInt(getTextElement(driver, CommonPageUI.SHOPPING_CART_ITEMS_TOTAL).replaceAll("[^0-9]", "")));

        List<WebElement> books = getListWebElements(driver, BooksPageUI.ITEMS_HAS_ADD_TO_CART);

        if(books.size() < 2) {
            throw new RuntimeException("There are not enough 2 items to add to cart");
        } else if(books.size() == 2) {
            for (WebElement element : books) {
                clickToElement(getWebElement(element, BooksPageUI.ADD_TO_CART_BTN));
                waitForElementInvisible(driver, CommonPageUI.LOADING_IMG);

                addedBookList.add(getTextElement(getWebElement(element, BooksPageUI.ITEM_NAME)));
            }
        } else {
                HashMap<WebElement, Integer> bookRating = new HashMap<>();

                for (WebElement book : books) {
                    bookRating.put(book, getPercentRatingOfBook(book));
                }
                bookRating.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(2)
                        .forEach((item) -> {
                            clickToElement(getWebElement(item.getKey(), BooksPageUI.ADD_TO_CART_BTN));
                            waitForElementInvisible(driver, CommonPageUI.LOADING_IMG);

                            addedBookList.add(getTextElement(getWebElement(item.getKey(), BooksPageUI.ITEM_NAME)));
                        });
        }
        AddToCartData.setProductList(addedBookList);
        return new HeaderObject(driver);
    }

    public int getPercentRatingOfBook(WebElement book) {
        WebElement ratingElement = getWebElement(book, BooksPageUI.RATINGS);
        return Integer.parseInt(getAttributeElement(ratingElement, "style").replaceAll("[^0-9]", ""));
    }

}

package com.vmo.demowebshop.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private Duration shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    private Duration longTimeout = GlobalConstants.LONG_TIMEOUT;
    private Select select;
    private WebDriverWait explicitWait;
    private JavascriptExecutor jsExecutor;
    private Actions actions;
    protected SoftAssert softAssert = new SoftAssert();

    public void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    public void overrideImplicitTimeOut(WebDriver driver, long timeout) {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
//    }

//    public boolean isElementUndisplayed(WebDriver driver, String locator) {
//        overrideImplicitTimeOut(driver, shortTimeOut);
//        List<WebElement> listElement = getListWebElements(driver, locator);
//        overrideImplicitTimeOut(driver, longTimeOut);
//        if (listElement.size() == 0) {
//            System.out.println("Element not in DOM");
//            return true;
//        } else if (!listElement.isEmpty() && !listElement.get(0).isDisplayed()) {
//            System.out.println("Element in DOM but undisplay");
//            return true;
//        } else {
//            System.out.println("Element in DOM and display");
//            return false;
//        }
//    }

//    public boolean isElementUndisplayed(WebDriver driver, String locator, String... params) {
//        locator = getDynamiLocator(locator, params);
//        overrideImplicitTimeOut(driver, shortTimeOut);
//        List<WebElement> listElement = getListWebElements(driver, locator);
//        overrideImplicitTimeOut(driver, longTimeOut);
//        if (listElement.size() == 0) {
//            System.out.println("Element not in DOM");
//            return true;
//        } else if (listElement.size() > 0 && !listElement.get(0).isDisplayed()) {
//            System.out.println("Element in DOM but undisplay");
//            return true;
//        } else {
//            System.out.println("Element in DOM and display");
//            return false;
//        }
//    }


    public void switchToWindowByCurrentID(WebDriver driver, String currentID) {
        Set<String> windows = driver.getWindowHandles();
        for(String id : windows) {
            if(!id.equals(currentID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> windows = driver.getWindowHandles();
        for(String id : windows) {
            driver.switchTo().window(id);
            if(driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    public void closeTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> windows = driver.getWindowHandles();
        for(String id : windows) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void switchToFrameByLocator(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver, String locator) {
        driver.switchTo().defaultContent();
    }

    public By getLocator(String locator) {
        String[] locatorSplit = locator.split(":=");
        String typeOfLocator = locatorSplit[0].toUpperCase();
        switch (typeOfLocator) {
            case "ID":
                return By.id(locatorSplit[1]);
            case "CLASS":
                return By.className(locatorSplit[1]);
            case "NAME":
                return By.name(locatorSplit[1]);
            case "CSS":
                return By.cssSelector(locatorSplit[1]);
            case "XPATH":
                return By.xpath(locatorSplit[1]);
            default:
                throw new RuntimeException("Does not support this type of location: " + typeOfLocator);
        }
    }

    public String getDynamicLocator(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getLocator(locator));
    }

    public WebElement getWebElement(WebElement element, String locator) {
        return element.findElement(getLocator(locator));
    }

    public WebElement getWebElement(WebDriver driver, String locator, String... params) {
        return driver.findElement(getLocator(getDynamicLocator(locator, params)));
    }

    public WebElement getWebElement(WebElement element, String locator, String... params) {
        return element.findElement(getLocator(getDynamicLocator(locator, params)));
    }

    public List<WebElement> getListWebElements(WebDriver driver, String locator) {
        return driver.findElements(getLocator(locator));
    }

    public List<WebElement> getListWebElements(WebDriver driver, String locator, String... params) {
        return driver.findElements(getLocator(getDynamicLocator(locator, params)));
    }

    public List<WebElement> getListWebElements(WebElement element, String locator) {
        return element.findElements(getLocator(locator));
    }

    public int getListElementsSize(WebDriver driver, String locator) {
        return  getListWebElements(driver, locator).size();
    }

    public int getListElementsSize(WebDriver driver, String locator, String... params) {
        return  getListWebElements(driver, locator, params).size();
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String ... params) {
        getWebElement(driver, locator, params).click();
    }

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String value) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(value);
    }

    public void sendKeysToElement(WebDriver driver, String locator, String value, String... param) {
        getWebElement(driver, locator, param).clear();
        getWebElement(driver, locator, param).sendKeys(value);
    }

    public void sendKeysToElement(WebDriver driver, String locator, Keys keys) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(keys);
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... param) {
        if(!isElementSelected(driver, locator, param)) {
            clickToElement(driver, locator, param);
        }
    }

    public void unCheckToCheckboxOrRadio(WebDriver driver, String locator, String... param) {
        if(isElementSelected(driver, locator, param)) {
            clickToElement(driver, locator, param);
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... param) {
        return getWebElement(driver, locator, param).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator, String... param) {
        return getWebElement(driver, locator, param).isEnabled();
    }

    public void selectItemInDefaultDropdownByText(WebDriver driver, String locator, String itemText) {
        select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    public void selectItemInDefaultDropdownByText(WebDriver driver, String locator, String itemText, String... params) {
        select = new Select(getWebElement(driver, locator, params));
        select.selectByVisibleText(itemText);
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator) {
        select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator, String... params) {
        select = new Select(getWebElement(driver, locator, params));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        jsExecutor = (JavascriptExecutor) driver;
        clickToElement(driver, parentLocator);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator(childLocator)));
        List<WebElement> listItems = getListWebElements(driver, childLocator);
        for (WebElement item : listItems) {
            if(item.getText().trim().equals(expectedItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true;", item);
                item.click();
                break;
            }
        }
    }

    public String getAttributeElement(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getAttributeElement(WebDriver driver, String locator, String attributeName, String... params) {
        return getWebElement(driver, locator, params).getAttribute(attributeName);
    }

    public String getAttributeElement(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    public String getTextElement(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getTextElement(WebDriver driver, String locator, String... params) {
        return getWebElement(driver, locator, params).getText();
    }

    public String getTextElement(WebElement element) {
        return element.getText();
    }


    public String getCssValueElement(WebDriver driver, String locator, String cssAttribute) {
        return getWebElement(driver, locator).getCssValue(cssAttribute);
    }

    public String getCssValueElement(WebDriver driver, String locator, String cssAttribute, String... params) {
        return getWebElement(driver, locator, params).getCssValue(cssAttribute);
    }

    //Action
    public void doubleClickToElement(WebDriver driver, String locator) {
        actions = new Actions(driver);
        actions.doubleClick(getWebElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator, String... params) {
        actions = new Actions(driver);
        actions.doubleClick(getWebElement(driver, locator, params));
    }


    public void hoverMouseToElement(WebDriver driver, String locator) {
        actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver, locator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locator, String... params) {
        actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver, locator, params)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        actions = new Actions(driver);
        actions.contextClick(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator, String... params) {
        actions = new Actions(driver);
        actions.contextClick(getWebElement(driver, locator, params)).perform();
    }

    public void dragAndDrop(WebDriver driver, String locatorSource, String locatorTarget) {
        actions = new Actions(driver);
        WebElement source = getWebElement(driver, locatorSource);
        WebElement target = getWebElement(driver, locatorTarget);
        actions.dragAndDrop(source, target).perform();
    }

    public void pressKeyboardToElementByActions(WebDriver driver, String locator, Keys key) {
        actions = new Actions(driver);
        actions.sendKeys(getWebElement(driver, locator), key).perform();
    }
    public void pressKeyboardToElement(WebDriver driver, String locator, Keys key) {
        getWebElement(driver, locator).sendKeys(key);
    }

    public void pressKeyboardToElement(WebDriver driver, String locator, Keys key, String... params) {
        getWebElement(driver, locator, params).sendKeys(key);
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToTopPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0,0)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");

        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void clickToElementByJS(WebDriver driver, String locator, String... params) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator, params));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator, String... params) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator, params));
    }

    public void scrollToElement(WebDriver driver, WebElement element) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public void sendKeyToElementByJS(WebDriver driver, String locator, String value, String... params) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator, params));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String... attributes) {
        jsExecutor = (JavascriptExecutor) driver;
        for(String attribute : attributes) {
            jsExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "');", getWebElement(driver, locator));

        }
    }

    public String getTextElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        String script = "return $(document.evaluate(" + "\"" + locator + "\"" + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()";
        return (String) jsExecutor.executeScript(script);
    }

    public String getTextElementByJS(WebDriver driver, String locator, String... params) {
        locator = getDynamicLocator(locator, params);
        jsExecutor = (JavascriptExecutor) driver;
        String script = "return $(document.evaluate(" + "\"" + locator + "\"" + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()";
        return (String) jsExecutor.executeScript(script);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;

        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator, String... params) {
        jsExecutor = (JavascriptExecutor) driver;

        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator, params));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));

    }

    public boolean isJQueryLoadSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                // TODO Auto-generated method stub
                return (Boolean) jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active===0);");
            }
        };
        return explicitWait.until(jQueryLoad);
    }

    public boolean isjQueryAndPageLoadSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                // TODO Auto-generated method stub
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active;") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> PageLoadSuccess = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                // TODO Auto-generated method stub
                return (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }

        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(PageLoadSuccess);
    }


    //wait
    public void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(getDynamicLocator(locator, params))));
    }
    public void waitForElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getLocator(getDynamicLocator(locator, params))));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getLocator(getDynamicLocator(locator, params))));
    }

    public void waitForAllElementsInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
    }

    public void waitForAllElementsInvisible(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator, params)));
    }

    public void waitForElementToBeSelected(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeSelected(getWebElement(driver, locator)));
    }
    public void waitForElementToBeSelected(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeSelected(getWebElement(driver, locator, params)));
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}

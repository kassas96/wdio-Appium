package utils.Actions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;

import java.time.Duration;
import java.util.Map;


public class MobileActions {


    /*Click on single element */
    public static void clickWhileVisible(AndroidDriver driver, By locator) {
        WaitHelper.waitVisibility(driver, locator);
        driver.findElement(locator).click();
    }

    /*Click on element from list */
    public static void clickWhileVisible(AndroidDriver driver, By locator, int index) {
        WaitHelper.waitVisibility(driver, locator);
        WebElement element = (WebElement) driver.findElements(locator).get(index);
        element.click();
    }

    /*Send keys to element*/
    public static void setTextWhileVisible(AndroidDriver driver, By locator, String text) {
        WaitHelper.waitVisibility(driver, locator);
        driver.findElement(locator).sendKeys(text);

    }

    /*Get Text from element */
    public static String getTextWhileVisible(AndroidDriver driver, By locator) {
        WaitHelper.waitVisibility(driver, locator);
        return driver.findElement(locator).getText();

    }

    public static void clearTextWhileVisible(AndroidDriver driver, By locator) {
        WaitHelper.waitVisibility(driver, locator);
        driver.findElement(locator).clear();
    }

    public static void moveToElementAndClick(AndroidDriver driver, By locator) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(locator)).click().perform();
        } catch (Exception e) {
            Logger.info("can't perform move and click element action" + e);
            throw new RuntimeException(e);
        }
    }

    public static void enter(AndroidDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    /*Check single element is clickable*/
    public static boolean isClickable(AndroidDriver driver, By locator, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * @param generalXpath a general Xpath text containing (%s) for text replacement
     * @param addedText    a test to be added in the general Xpath
     * @return an element locator with specificXpath
     */
    public static By getElementUsingGeneralXpath(String generalXpath, String addedText) {
        String specificXpath = String.format(generalXpath, addedText);
        return By.xpath(specificXpath);
    }
    public void scrollToText(AndroidDriver driver, String text) {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 100, "top", 100, "width", 200, "height", 200,
                        "direction", "down",
                        "percent", 3.0
                )
        );
    }

    /*Scroll to element using Appium one to be compatible with seetest */
    public static void scrollToElement(AndroidDriver driver, By locator) {
        boolean end_of_page = false;
        String source_page = driver.getPageSource();
        int maxScroll = 6;
        while (!end_of_page && maxScroll > 0) {
            if (isElementDisplay(driver, locator)) {
                return;
            }
            Dimension dimension = driver.manage().window().getSize();
            int scrollStart = (int) (dimension.getHeight() * 0.8);
            int scrollEnd = (int) (dimension.getHeight() * 0.3);
            new TouchAction(driver).press(PointOption.point(0, scrollStart)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(0, scrollEnd)).release().perform();
            end_of_page = source_page.equals(driver.getPageSource());
            source_page = driver.getPageSource();
            maxScroll--;
        }
    }

    public static boolean isElementDisplay( AndroidDriver driver,By locator) {
        boolean isDisplyed = false;
        try {
            new WebDriverWait( driver, 50).until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            isDisplyed = true;
        } catch (Exception e) {
            System.out.println("Element not Displayed " + locator);
            e.printStackTrace();
            isDisplyed = false;
        }
        return isDisplyed;
    }

    /*Check element on list is clickable*/
    public static boolean isClickable( AndroidDriver driver, By locator, int index, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable((WebElement) driver.findElements(locator).get(index)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     *
     * @param driver
     * @param targetLocator  the desired locator
     * @param baseLocator   the base of the indexed locator
     */
//    public static void swipeLeftOnCards(AndroidDriver driver, By targetLocator, By baseLocator) {
//
//        int tobLocationHeight = driver.findElement(baseLocator).getLocation().getY()+(driver.findElement(baseLocator).getSize().getHeight()/2);
//        boolean end_of_page = false;
//        String source_page = driver.getPageSource();
//        while (!end_of_page) {
//            if (isElementDisplay(driver, targetLocator)) {
//                return;
//            }
//            Dimension dimension = driver.manage().window().getSize();
//            int scrollStart = (int) (dimension.getWidth() * 0.7);
//            int scrollEnd = (int) (dimension.getWidth() * 0.3);
//            new TouchAction(driver)
//                    .press(PointOption.point( scrollStart, tobLocationHeight))
//                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
//                    .moveTo(PointOption.point(scrollEnd,tobLocationHeight)).release().perform();
//            end_of_page = source_page.equals(driver.getPageSource());
//            source_page = driver.getPageSource();
//        }
//    }
    public static void swipeLeftOnCards(AndroidDriver driver, By targetLocator, By baseLocator) {
        int tobLocationHeight = driver.findElement(baseLocator).getLocation().getY() +
                (driver.findElement(baseLocator).getSize().getHeight()/2);
        boolean end_of_page = false;
        boolean elementFound = false;
        String source_page = driver.getPageSource();
        int swipeCountAfterFound = 0;
        final int MAX_SWIPES_AFTER_FOUND = 1;

        while (!end_of_page) {
            // Check if element is displayed
            if (isElementDisplay(driver, targetLocator)) {
                elementFound = true;

                if (swipeCountAfterFound >= MAX_SWIPES_AFTER_FOUND) {
                    return;
                }
            }

            // Perform the swipe
            Dimension dimension = driver.manage().window().getSize();
            int scrollStart = (int) (dimension.getWidth() * 0.7);
            int scrollEnd = (int) (dimension.getWidth() * 0.3);

            new TouchAction(driver)
                    .press(PointOption.point(scrollStart, tobLocationHeight))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))) // Reduced from 2 seconds
                    .moveTo(PointOption.point(scrollEnd, tobLocationHeight))
                    .release()
                    .perform();

            // Count extra swipes if we've found the element
            if (elementFound) {
                swipeCountAfterFound++;
            }

            // Check if we've reached the end of the page
            end_of_page = source_page.equals(driver.getPageSource());
            source_page = driver.getPageSource();
        }
    }

}

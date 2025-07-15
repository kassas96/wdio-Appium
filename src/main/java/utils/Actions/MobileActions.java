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

/**
 * Utility class providing mobile-specific actions for Appium test automation.
 * Contains methods for interacting with mobile elements, gestures, and validations.
 */
public class MobileActions {

    /**
     * Clicks on a visible element located by the specified locator.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     * @throws NoSuchElementException If the element is not found or not visible
     */
    public static void clickWhileVisible(AndroidDriver driver, By locator) {
        WaitHelper.waitVisibility(driver, locator);
        driver.findElement(locator).click();
    }

    /**
     * Clicks on a visible element from a list of elements located by the specified locator.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find elements
     * @param index   The index of the element in the list
     * @throws NoSuchElementException If the element is not found or not visible
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    public static void clickWhileVisible(AndroidDriver driver, By locator, int index) {
        WaitHelper.waitVisibility(driver, locator);
        WebElement element = (WebElement) driver.findElements(locator).get(index);
        element.click();
    }

    /**
     * Enters text into a visible element located by the specified locator.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     * @param text    The text to enter into the element
     * @throws NoSuchElementException If the element is not found or not visible
     */
    public static void setTextWhileVisible(AndroidDriver driver, By locator, String text) {
        WaitHelper.waitVisibility(driver, locator);
        driver.findElement(locator).sendKeys(text);
    }

    /**
     * Retrieves text from a visible element located by the specified locator.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     * @return The text content of the element
     * @throws NoSuchElementException If the element is not found or not visible
     */
    public static String getTextWhileVisible(AndroidDriver driver, By locator) {
        WaitHelper.waitVisibility(driver, locator);
        return driver.findElement(locator).getText();
    }

    /**
     * Clears text from a visible element located by the specified locator.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     * @throws NoSuchElementException If the element is not found or not visible
     */
    public static void clearTextWhileVisible(AndroidDriver driver, By locator) {
        WaitHelper.waitVisibility(driver, locator);
        driver.findElement(locator).clear();
    }

    /**
     * Moves to the specified element and clicks on it.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     * @throws RuntimeException If the move and click action cannot be performed
     */
    public static void moveToElementAndClick(AndroidDriver driver, By locator) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(locator)).click().perform();
        } catch (Exception e) {
            Logger.info("can't perform move and click element action" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Simulates pressing the ENTER key.
     *
     * @param driver The AndroidDriver instance
     */
    public static void enter(AndroidDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    /**
     * Checks if an element is clickable within the specified time.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     * @param time    The maximum time to wait in seconds
     * @return true if the element is clickable, false otherwise
     */
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
     * Creates a dynamic XPath locator by inserting text into a template.
     *
     * @param generalXpath The XPath template containing (%s) for text replacement
     * @param addedText    The text to insert into the XPath template
     * @return A By locator with the constructed XPath
     */
    public static By getElementUsingGeneralXpath(String generalXpath, String addedText) {
        String specificXpath = String.format(generalXpath, addedText);
        return By.xpath(specificXpath);
    }

    /**
     * Scrolls to an element containing the specified text.
     *
     * @param driver The AndroidDriver instance
     * @param text   The text to scroll to
     */
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

    /**
     * Scrolls to an element located by the specified locator.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     */
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
            new TouchAction(driver).press(PointOption.point(0, scrollStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(0, scrollEnd))
                    .release().perform();
            end_of_page = source_page.equals(driver.getPageSource());
            source_page = driver.getPageSource();
            maxScroll--;
        }
    }

    /**
     * Checks if an element is displayed on the screen.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find the element
     * @return true if the element is displayed, false otherwise
     */
    public static boolean isElementDisplay(AndroidDriver driver, By locator) {
        boolean isDisplyed = false;
        try {
            new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            isDisplyed = true;
        } catch (Exception e) {
            System.out.println("Element not Displayed " + locator);
            e.printStackTrace();
            isDisplyed = false;
        }
        return isDisplyed;
    }

    /**
     * Checks if an element from a list is clickable within the specified time.
     *
     * @param driver  The AndroidDriver instance
     * @param locator The By locator strategy to find elements
     * @param index   The index of the element in the list
     * @param time    The maximum time to wait in seconds
     * @return true if the element is clickable, false otherwise
     */
    public static boolean isClickable(AndroidDriver driver, By locator, int index, int time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable((WebElement) driver.findElements(locator).get(index)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Performs a swipe left gesture on cards until the target element is found and fully visible.
     * Continues to swipe once more after finding the element to ensure it's fully in view.
     *
     * @param driver        The AndroidDriver instance
     * @param targetLocator The By locator for the element to find
     * @param baseLocator   The By locator for the container element
     */
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
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
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
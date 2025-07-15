package utils.Actions;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;


public abstract class WaitHelper {

    /**
     * Waits for an element to become visible on the screen.
     *
     * @param driver The AndroidDriver instance used to interact with the app
     * @param locator The By locator strategy used to identify the element
     * @return true if the element becomes visible within the timeout period, false otherwise
     * */
    public static boolean waitVisibility(AndroidDriver driver, By locator) {
        boolean isElementPresent = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
            isElementPresent = true;
        } catch (Exception e) {
            Logger.info("The element on waitVisibility method not found " + e);
        }
        return isElementPresent;
    }

    /**
       Pauses test execution for the specified amount of time.
     * This is a static wait and should be used sparingly in favor of explicit waits.
     *
     * @param timeoutInMilliSeconds The duration to pause execution in milliseconds
     */
    public static void waitElement(int timeoutInMilliSeconds) {
        try {
            Thread.sleep(timeoutInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package utils.Actions;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;


public abstract class WaitHelper {

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
     * @param timeoutInMilliSeconds the length of time to sleep in milliseconds
     */
    public static void waitElement(int timeoutInMilliSeconds) {
        try {
            Thread.sleep(timeoutInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

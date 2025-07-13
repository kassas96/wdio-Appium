package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.Actions.MobileActions;

public class HomePage {
    AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * -------------------------------Home page Locators -----------------------------------------
     **/
    private static By HOMEPAGE_MAIN_TITLE = By.xpath("//*[@text='WEBDRIVER' and @class='android.widget.TextView']");
    public static By HOMEPAGE_TAB = By.xpath("//*[@text='Home' and @class='android.widget.TextView']");
    public static By LOGIN_TAB = By.xpath("//*[@text='Login' and @class='android.widget.TextView']");
    public static By FORMS_TAB = By.xpath("//*[@text='Forms' and @class='android.widget.TextView']");
    public static By SWIPE_TAB = By.xpath("//*[@text='Swipe' and @class='android.widget.TextView']");


    public  boolean verifyIamInHomePage(){
        return MobileActions.isElementDisplay(driver,HOMEPAGE_MAIN_TITLE);
    }
    public void navigateToLoginScreen() {
        MobileActions.clickWhileVisible(driver,LOGIN_TAB);
    }

    public void navigateToFormsScreen() {
        MobileActions.clickWhileVisible(driver,FORMS_TAB);
    }

    public void navigateToSwipeScreen() {
        MobileActions.clickWhileVisible(driver,SWIPE_TAB);
    }
}

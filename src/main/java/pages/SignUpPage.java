package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.Actions.MobileActions;

public class SignUpPage {
    AndroidDriver driver;

    public SignUpPage(AndroidDriver driver) {
        this.driver = driver;
    }
    /**
     * -------------------------------Signup page Locators -----------------------------------------
     **/
    private static final By SIGNUP_PAGE_EMAIL = MobileBy.AccessibilityId("input-email");
    private static final By SIGNUP_PAGE_PASSWORD = MobileBy.AccessibilityId("input-password");
    private static final By SIGNUP_PAGE_CONFIRMATION_PASSWORD = MobileBy.AccessibilityId("input-repeat-password");

    private static final By SIGNUP_PAGE_SIGNUP_BTN  =  MobileBy.AccessibilityId("button-SIGN UP");
    private static final By SIGNUP_SUCCESS_MSG = By.xpath("//*[@text='You successfully signed up!']");
    private static final By SIGNUP_SUCCESS_POPUP_OK_BTN = By.id("android:id/button1");


    public void completeSignUp( String email, String password, String confirmPassword) {
        MobileActions.setTextWhileVisible(driver,SIGNUP_PAGE_EMAIL,email);
        MobileActions.setTextWhileVisible(driver,SIGNUP_PAGE_PASSWORD,password);
        MobileActions.setTextWhileVisible(driver,SIGNUP_PAGE_CONFIRMATION_PASSWORD,confirmPassword);
        MobileActions.clickWhileVisible(driver,SIGNUP_PAGE_SIGNUP_BTN);

    }
    public boolean isSignUpSuccessful() {
        return MobileActions.isElementDisplay(driver,SIGNUP_SUCCESS_MSG);
    }
    public void closePopUpMsg(){
        MobileActions.clickWhileVisible(driver,SIGNUP_SUCCESS_POPUP_OK_BTN);
    }

}

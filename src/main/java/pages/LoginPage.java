package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.Actions.MobileActions;
import utils.Actions.WaitHelper;

public class LoginPage {
AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * -------------------------------Login page Locators -----------------------------------------
     **/
    private static final By LOGIN_TAB = By.xpath("//*[@content-desc='button-login-container']");
    private static final By LOGIN_PAGE_MAIN_TITLE = By.xpath("//*[@text='Login / Sign up Form']");
    private static final By LOGIN_PAGE_EMAIL = MobileBy.AccessibilityId("input-email");
    private static final By LOGIN_PAGE_PASSWORD = MobileBy.AccessibilityId("input-password");

    private static final By LOGIN_PAGE_LOGIN_BTN  = By.xpath("//*[@content-desc='button-LOGIN']");
    private static final By LOGIN_SUCCESS_MSG = By.xpath("[@text='You are logged in!']");

    public  boolean verifyIamInLoginPage(){
        return MobileActions.isElementDisplay(driver,LOGIN_PAGE_MAIN_TITLE);
    }
    private void enterEmailInLoginPage(String email){
        MobileActions.setTextWhileVisible(driver,LOGIN_PAGE_EMAIL,email);
    }
    private void enterPasswordInLoginPage(String Password){
        MobileActions.setTextWhileVisible(driver,LOGIN_PAGE_PASSWORD,Password);
    }
    private void clickOnLoginBtn(){
        MobileActions.clickWhileVisible(driver,LOGIN_PAGE_LOGIN_BTN);
    }
    public void completeSignUp( String email, String password) {
        enterEmailInLoginPage(email);
        enterPasswordInLoginPage(password);
        clickOnLoginBtn();
    }
    public boolean isLoginSuccessful() {
        return MobileActions.isElementDisplay(driver,LOGIN_SUCCESS_MSG);
    }
    public void clickOnLoginTab(){
        MobileActions.clickWhileVisible(driver,LOGIN_PAGE_LOGIN_BTN);
    }
}

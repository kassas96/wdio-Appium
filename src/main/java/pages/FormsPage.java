package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.Actions.MobileActions;

public class FormsPage {
    AndroidDriver driver;

    public FormsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * ------------------------------- Forms page Locators -----------------------------------------
     **/
    private static final By FORM_PAGE_HEADER = By.xpath("//*[@text='Form components']");
    private static final By TEXT_INPUT_FIELD = MobileBy.AccessibilityId("text-input");
    private static final By TEXT_INPUT_RESULT = MobileBy.AccessibilityId("input-text-result");
    private static final By SWITCH_ELEMENT = MobileBy.AccessibilityId("switch");
    private static final By SWITCH_TEXT = MobileBy.AccessibilityId("switch-text");
    private static final By DROPDOWN = By.xpath("//*[@resource-id='text_input']");
    private static final By DROPDOWN_OPTION = By.xpath("//*[@text='Appium is awesome']");
    private static final By ACTIVE_BUTTON = MobileBy.AccessibilityId("button-Active");
    private static final By INACTIVE_BUTTON = By.xpath("//*[@content-desc='button-Inactive']");


    private static final By ACTIVE_DIALOG_TITLE = By.id("android:id/message");
    private static final By ACTIVE_DIALOG_OK_BUTTON= By.id("android:id/button1");

    /**
     * ------------------------------- Forms page Actions -----------------------------------------
     **/
    public boolean verifyIamInFormPage(){
        return MobileActions.isElementDisplay(driver,FORM_PAGE_HEADER);
    }
    public void enterText(String text) {
        MobileActions.setTextWhileVisible(driver, TEXT_INPUT_FIELD, text);
    }

    public String getEnteredText() {
        return MobileActions.getTextWhileVisible(driver, TEXT_INPUT_RESULT);
    }

    public void toggleSwitch() {
        MobileActions.clickWhileVisible(driver, SWITCH_ELEMENT);
    }

    public String getSwitchText() {
        return MobileActions.getTextWhileVisible(driver, SWITCH_TEXT);
    }

    public void selectDropdownOption() {
        MobileActions.clickWhileVisible(driver, DROPDOWN);
        MobileActions.clickWhileVisible(driver, DROPDOWN_OPTION);
    }
    public String getDropdownSelectedText() {
        return MobileActions.getTextWhileVisible(driver, DROPDOWN);
    }
    public void clickActiveButton() {
        MobileActions.clickWhileVisible(driver, ACTIVE_BUTTON);
    }
    public boolean isInActiveBtnClickable(){
       return MobileActions.isClickable(driver,INACTIVE_BUTTON,1000);
    }

    public String getActiveDialogTitle() {
        return MobileActions.getTextWhileVisible(driver, ACTIVE_DIALOG_TITLE);
    }

    public boolean isActiveDialogDisplayed() {
        return MobileActions.isElementDisplay(driver, ACTIVE_DIALOG_TITLE);
    }
    public void clickActiveOKButton() {
        MobileActions.clickWhileVisible(driver, ACTIVE_DIALOG_OK_BUTTON);
    }
}
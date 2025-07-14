package pages;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import utils.Actions.MobileActions;


public class SwipePage {
    AndroidDriver driver;

    public SwipePage(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * ------------------------------- Swipe page Locators -----------------------------------------
     **/
    private static final By SUPPORT_VIDEOS_CARD = By.xpath("//*[@text='SUPPORT VIDEOS']");
    private static final By CARD_CONTAINER = By.xpath("//*[@content-desc='card']");

    /**
     * ------------------------------- Swipe page Actions -----------------------------------------
     **/
    public void swipeToSupportVideos() {
         MobileActions.swipeLeftOnCards(driver, SUPPORT_VIDEOS_CARD, CARD_CONTAINER);
    }

    public boolean isSupportVideosDisplayed() {
        return MobileActions.isElementDisplay(driver, SUPPORT_VIDEOS_CARD);
    }
}
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DataReader.PropertiesReader;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public AndroidDriver<AndroidElement> driver;

    @BeforeClass
    public void setupAppium() throws IOException {
        // Load properties file
        PropertiesReader prop = new PropertiesReader("src/main/resources/config.properties");

        DesiredCapabilities caps = new DesiredCapabilities();
        //mandatory capabilities
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
        caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + prop.getProperty("appPath"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //optional capabilities
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,prop.getProperty("platformVersion"));
        caps.setCapability(MobileCapabilityType.NO_RESET, true);
        caps.setCapability("autoGrantPermissions", true);

        driver = new AndroidDriver<>(new URL(prop.getProperty("appiumURL")), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

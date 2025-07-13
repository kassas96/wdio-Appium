package utils.Setup;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Setup {
    public AndroidDriver<AndroidElement> driver;
    public Properties prop;

    @BeforeClass
    public void configureAppium() throws IOException {
        // Load properties file
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        prop.load(fis);

        DesiredCapabilities caps = new DesiredCapabilities();
        //mandatory capabilities
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
        caps.setCapability(MobileCapabilityType.APP,  prop.getProperty("appPath"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //optional capabilities
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

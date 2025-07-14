import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.Actions.WaitHelper;
import utils.DataReader.JsonFileManager;

public class WDIOTest extends TestBase{
    private HomePage homePage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private FormsPage formsPage;
    private SwipePage swipePage;
    private JsonFileManager testData;

    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        formsPage = new FormsPage(driver);
        swipePage = new SwipePage(driver);
        testData =  new JsonFileManager(System.getProperty("user.dir") + "/src/test/resources/testData.json");
    }

    @Test(priority = 1, description = "Test user sign up and login flow")
    public void testSignUpAndLogin() {
        // Navigate to Login screen
        homePage.navigateToLoginScreen();

        // Navigate to Sign Up tab
        loginPage.navigateToSignUpPage();

        // perform sign up
       String email= testData.getTestData("Email");
       String password= testData.getTestData("Password");
       signUpPage.completeSignUp(email,password,password);

        // Assert sign up was successful
        Assert.assertTrue(signUpPage.isSignUpSuccessful(), "Sign up was failed");

        //close the popup
        signUpPage.closePopUpMsg();

        // Navigate to Login tab
        loginPage.navigateToLoginPage();

        //perform login
        loginPage.completeLogin(email,password);

        // Assert login was successful
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful");

        //close the popup
        loginPage.clickOnSuccessMsgOkBtn();
    }

    @Test(priority = 2, description = "Test forms page interactions")
    public void testFormsScreen() {
        // Navigate to Forms screen
        homePage.navigateToFormsScreen();

       // verify successful navigation toFormPage
        formsPage.verifyIamInFormPage();

        // Test text input
        String testText = testData.getTestData("DataForInputField");
        formsPage.enterText( testText);
        Assert.assertEquals(formsPage.getEnteredText(), testText, "Text input doesn't match");

        // Test switch
        formsPage.toggleSwitch();
        Assert.assertEquals(formsPage.getSwitchText(), "Click to turn the switch OFF", "Switch text doesn't match");

        // Select from dropdown then assert on the selection
        formsPage.selectDropdownOption();
        Assert.assertEquals(formsPage.getDropdownSelectedText(),"Appium is awesome","selection failed");

        // Test active button then close the popup
        formsPage.clickActiveButton();
        Assert.assertEquals(formsPage.getActiveDialogTitle(), "This button is active", "Active dialog title doesn't match");
        formsPage.clickActiveOKButton();

        // Test inactive button
        Assert.assertFalse(formsPage.isActiveDialogDisplayed(),"the inactive btn is active now");

    }

    @Test(priority = 3, description = "Test swipe functionality")
    public void testSwipeScreen() {
        // Navigate to Swipe screen
        homePage.navigateToSwipeScreen();

        // Swipe until Support Videos card is displayed
        swipePage.swipeToSupportVideos();

        // Assert Support Videos card is displayed
        Assert.assertTrue(swipePage.isSupportVideosDisplayed(), "Support Videos card is not displayed");
    }

}

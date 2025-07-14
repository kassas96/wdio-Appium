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

    @DataProvider(name = "userData")
    public Object[][] dataprov(){
       return new Object[][]{
               {  "modey",25},
               {"kas",29}
        };
    }

//    @Parameters({"name","age"})
//    @Test(groups = "smoke")
//    public void test1(String name, int age){
//        System.out.println(name+age);
//    }

//    @Test(groups = "regression")
//    public void test2(){
//        WaitHelper.waitElement(2000000);
//        System.out.println("hello");
//    }
//
//
//    @Test(dataProvider = "userData",groups = "smoke")
//    public void test3(String name, int age){
//        System.out.println(name+age);
//    }

    @Test(priority = 1)
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

//    @Test(priority = 2)
//    public void testFormsScreen() {
//        // Navigate to Forms screen
//        welcomePage.navigateToFormsScreen(driver);
//
//        // Test text input
//        String testText = testData.getValue("formsTestText");
//        formsPage.enterText(driver, testText);
//        Assert.assertEquals(formsPage.getEnteredText(), testText, "Text input doesn't match");
//
//        // Test switch
//        formsPage.toggleSwitch(driver);
//        Assert.assertEquals(formsPage.getSwitchText(), "Click to turn the switch OFF", "Switch text doesn't match");
//
//        // Test dropdown
//        formsPage.selectDropdownOption(driver);
//
//        // Test active button
//        formsPage.clickActiveButton(driver);
//        Assert.assertEquals(formsPage.getActiveDialogTitle(), "This button is active", "Active dialog title doesn't match");
//    }
//
//    @Test(priority = 3)
//    public void testSwipeScreen() {
//        // Navigate to Swipe screen
//        welcomePage.navigateToSwipeScreen(driver);
//
//        // Swipe until Support Videos card is displayed
//        swipePage.swipeToSupportVideos(driver);
//
//        // Assert Support Videos card is displayed
//        Assert.assertTrue(swipePage.isSupportVideosDisplayed(driver), "Support Videos card is not displayed");
//    }

}

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{

    @DataProvider(name = "userData")
    public Object[][] dataprov(){
       return new Object[][]{
               {  "modey",25},
               {"kas",29}
        };
    }

    @Parameters({"name","age"})
    @Test(groups = "smoke")
    public void test1(String name, int age){
        System.out.println(name+age);
    }

    @Test(groups = "regression")
    public void test2(){
        System.out.println("hello");
    }


    @Test(dataProvider = "userData",groups = "smoke")
    public void test3(String name, int age){
        System.out.println(name+age);
    }
}

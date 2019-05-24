import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class UnitTestBaseClass {


    @BeforeSuite
    public void globalSetup(){

        System.out.println("Global setup function ");

    }


    @BeforeMethod
    public void globalmethod(){

        System.out.println("Global method");

    }






}

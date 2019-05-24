import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UnitTestWithDataProviders {


    UserManager um;


    @BeforeMethod
    public void setup() {

        //Arrange

        um = new UserManager();

    }




    @DataProvider
    protected Object[][] invalidEmailProvider(){

        return new Object[][]{

                {""},
                {"johnmail.com"},
                {"john@emailcom"}

        };




    }

    @Test(dataProvider ="invalidEmailProvider", expectedExceptions = IllegalArgumentException.class)
    public void emptyUserThrowsException(String invalidEmail) {

        //Act

        boolean result = um.addUser(invalidEmail);

        //Assert

        Assert.assertTrue(result);

    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//
//
//    public void userWithoutAtSignThrowsException(){
//
//
//        //Act
//        boolean result =um.addUser("myself.com");
//
//        //Assert
//
//        Assert.assertEquals(result,true);
//
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//
//
//    public void userWithoutDotException(){
//
//
//        //Act
//        boolean result =um.addUser("myself@com");
//
//        //Assert
//
//        Assert.assertEquals(result,true);
//
//    }
//
//
//

}

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testuser extends UnitTestBaseClass {

    UserManager um= new UserManager();

    @BeforeClass
            public void setup(){


        System.out.println("Runs one per class");




    }

    int i=0;
    @BeforeMethod
    public void set(){

        um= new UserManager();
        i++;
        System.out.println("Test No: "+i);

    }
    @Test
    public void successfulAddUserReturnsTrue(){
        //Arrange

         //Act

        boolean result=um.addUser("myself.yash.srivastav@gmail.com");

        //Assert

        Assert.assertTrue(result);


    }

    @Test(/*description = "if not present null should be returned",expectedExceptions = DuplicateRequestException.class, timeout=500,enabled=false,Ignore is and alternative of enabled but setup functions run in ignore and not in enabled, alwaysRun=true*/)
    public void getUserReturnsExistingUser(){
        //Arrange


        um.addUser("myself.yash.srivastav@gmail.com");


        //Act

        String result=um.getUser("myself.yash.srivastav@gmail.com");

        //Assert

        Assert.assertEquals(result,"myself.yash.srivastav@gmail.com");
    }

    @Test(invocationCount = 2)
    public void getNonExistingUser(){
        //Arrange

        um.addUser("myself.yash.srivastav@gmail.com");
        //Act
        String result=um.getUser("Kavybro@gmail.com");

        //Assert

        Assert.assertNull(result,"result should be null if user is not found");

    }

}

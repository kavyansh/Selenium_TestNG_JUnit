import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UiTestWithDataProviders{

    @DataProvider
    public Object[][] emailDataProvider(){

         return new Object[][]{


                 {"yash@com"},
                 {"yash.com"},
                 {"yash@.com"},
                 {""}

         };

    }
    @DataProvider
    public Object[][] shortPasswords(){

        return new Object[][]{
                {"123"},
                {"yas"},
                {"@!$"},
                {""}


        };

    }

    @DataProvider
    public Object[][] wrongDay(){

        return new Object[][]{

                {"-1"},
                {"36"},
        };

    }
    @DataProvider
    public Object[][] wrongYear(){


        return new Object[][]{

                {"19999"},
                {"0000"},
                {"0"}

        };

    }











    @BeforeMethod
    public void setup() throws InterruptedException {

        System.out.println("Before Method is Finished");

}


@Test
public void emailEmptyCheck() throws InterruptedException {

    //Act
    System.setProperty("webdriver.gecko.driver","C:\\Users\\Yash\\Desktop\\geckodriver-master\\geckodriver.exe");
    WebElement email;
    WebElement password;
    WebDriver driver=new FirefoxDriver();
    WebElement confirmEmail;
    driver.get("https://www.spotify.com/ca-en/signup/");
    Thread.sleep(3000);
    email=driver.findElement(By.id("register-email"));
    confirmEmail=driver.findElement(By.id("register-confirm-email"));

    password=driver.findElement(By.id("register-password"));
email.sendKeys("");
confirmEmail.sendKeys("");

password.sendKeys("yash");
    Thread.sleep(3000);
    WebElement signup=driver.findElement((By.xpath("//*[@id=\"register-button-email-submit\"]")));
    signup.click();

    Thread.sleep(3000);

WebElement result=driver.findElement(By.xpath("//*[@id=\"js-register-with-email\"]/fieldset/ul/li[1]/label[2]"));

//Assert

    System.out.println(result.getText());

    Assert.assertEquals(result.getText(),"Please enter your email.");

}


@Test(dataProvider = "emailDataProvider")
    public void emailWithoutDotCheck( String emailz) throws InterruptedException {

        //Arrange
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Yash\\Desktop\\geckodriver-master\\geckodriver.exe");
        WebElement email;
        WebElement password;
        WebDriver driver=new FirefoxDriver();
        WebElement confirmEmail;
        driver.get("https://www.spotify.com/ca-en/signup/");
        Thread.sleep(3000);
        email=driver.findElement(By.id("register-email"));
        confirmEmail=driver.findElement(By.id("register-confirm-email"));
        password=driver.findElement(By.id("register-password"));
        email.sendKeys(emailz);
        confirmEmail.sendKeys(emailz);
        password.sendKeys("yash");
        Thread.sleep(3000);
        WebElement signup=driver.findElement((By.xpath("//*[@id=\"register-button-email-submit\"]")));
        signup.click();

        Thread.sleep(3000);

        WebElement result=driver.findElement(By.xpath("//*[@id=\"js-register-with-email\"]/fieldset/ul/li[1]/label[2]"));

//Assert
        System.out.println(result.getText());

        if(emailz!="") {

            Assert.assertEquals(result.getText(), "The email address you supplied is invalid.");
        }
        else{

            Assert.assertEquals(result.getText(), "Please enter your email.");


        }
        }


    @Test
    public void whatShouldWeCallYou() throws InterruptedException {

        //arrange

        System.setProperty("webdriver.gecko.driver","C:\\Users\\Yash\\Desktop\\geckodriver-master\\geckodriver.exe");

        WebDriver driver=new FirefoxDriver();

        driver.get("https://www.spotify.com/ca-en/signup/");
        Thread.sleep(3000);

        WebElement name=driver.findElement(By.id("register-displayname"));

        name.sendKeys("");

        Thread.sleep(3000);
        WebElement signup=driver.findElement((By.xpath("//*[@id=\"register-button-email-submit\"]")));
        signup.click();

        Thread.sleep(3000);

        WebElement result=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/section[2]/div/form/fieldset/ul/li[4]/label[2]"));

//Assert
        System.out.println(result.getText());

        Assert.assertEquals(result.getText(),"What should we call you?");





    }






    @Test(dataProvider = "shortPasswords")
    public void passwordIsTooShort( String shortpasswords) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Yash\\Desktop\\geckodriver-master\\geckodriver.exe");

        WebDriver driver=new FirefoxDriver();
        driver.get("https://www.spotify.com/ca-en/signup/");
        Thread.sleep(3000);
        WebElement password;
        WebElement error;
        password=driver.findElement(By.id("register-password"));
        password.sendKeys(shortpasswords);
        Thread.sleep(3000);
        WebElement signup=driver.findElement((By.xpath("//*[@id=\"register-button-email-submit\"]")));
        signup.click();
        Thread.sleep(3000);
        error=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/section[2]/div/form/fieldset/ul/li[3]/label[2]"));
        System.out.println( error.getText());

        //Assert
        if(shortpasswords!="") {
            Assert.assertEquals(error.getText(), "Your password is too short.");
        }else{

            Assert.assertEquals(error.getText(), "Please choose a password.");



        }


    }



    @Test(dataProvider = "wrongDay")
    public void daycheck(String wrongday) throws InterruptedException {


        System.setProperty("webdriver.gecko.driver","C:\\Users\\Yash\\Desktop\\geckodriver-master\\geckodriver.exe");
        WebDriver driver=new FirefoxDriver();
        driver.get("https://www.spotify.com/ca-en/signup/");
        Thread.sleep(3000);
        WebElement day;
        WebElement error;
        day=driver.findElement(By.id("register-dob-day"));
        day.sendKeys(wrongday);
        Thread.sleep(3000);
        WebElement signup=driver.findElement((By.xpath("//*[@id=\"register-button-email-submit\"]")));
        signup.click();
        Thread.sleep(3000);
        error=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/section[2]/div/form/fieldset/ul/li[5]/label[2]"));
        System.out.println( error.getText());
        //Assert
        Assert.assertEquals(error.getText(),"Please enter a valid day of the month.");

    }


    @Test(dataProvider = "wrongYear")

    public void yearCheck(String wrongyear) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver","C:\\Users\\Yash\\Desktop\\geckodriver-master\\geckodriver.exe");
        WebDriver driver=new FirefoxDriver();
        driver.get("https://www.spotify.com/ca-en/signup/");
        Thread.sleep(3000);
        WebElement year;
        WebElement error;
        year=driver.findElement(By.id("register-dob-year"));
        year.sendKeys(wrongyear);
        Thread.sleep(3000);
        WebElement signup=driver.findElement((By.xpath("//*[@id=\"register-button-email-submit\"]")));
        signup.click();
        Thread.sleep(3000);
        error=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/section[2]/div/form/fieldset/ul/li[5]/label[2]"));
        System.out.println( error.getText());
        //Assert
        Assert.assertEquals(error.getText(),"Please enter a valid year.");
    }
    @Test
    public void checkTermsAndConditions() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Yash\\Desktop\\geckodriver-master\\geckodriver.exe");
        WebDriver driver=new FirefoxDriver();
        driver.get("https://www.spotify.com/ca-en/signup/");
        Thread.sleep(3000);
        WebElement error;
        Thread.sleep(3000);
        WebElement signup=driver.findElement((By.xpath("//*[@id=\"register-button-email-submit\"]")));
        signup.click();
        Thread.sleep(3000);
        error=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/section[2]/div/form/fieldset/ul/li[8]/label[2]"));
        System.out.println( error.getText());
        WebElement error2=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/section[2]/div/form/fieldset/ul/li[6]/label[5]"));
        //Assert
        Assert.assertEquals(error.getText(),"Please accept the terms and conditions to continue.");
        Assert.assertEquals(error2.getText(),"Please indicate your gender.");

    }
@AfterMethod
    public void aftermethod(){
        System.out.println("After method is finished");

}

}









import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class WebServiceTestSoftAssert {

    CloseableHttpClient client = HttpClientBuilder.create().build();




    @BeforeClass
    public void setup() throws IOException {


        client=HttpClientBuilder.create().build();
        CloseableHttpResponse response=client.execute(new HttpGet("https://api.github.com/"));
        int ACTUALStatuscode=response.getStatusLine().getStatusCode();

        if(ACTUALStatuscode!=300){

            throw new SkipException("Basic criteria failed");

        }
        System.out.println("Runs one per class");
    }

    @Test(groups = { "bonding", "strong_ties" },dependsOnMethods = "setup",priority=2)
    public void hardAssertStopImmediately () throws IOException {

        //Arrange
        CloseableHttpClient client = HttpClientBuilder.create().build();
        SoftAssert sa= new SoftAssert();
        //Act

        CloseableHttpResponse response=client.execute(new HttpGet("https://api.github.com/"));

        //Assert
        System.out.println("First Assert");

        sa.assertEquals(response.getStatusLine().getStatusCode(),404);

        System.out.println("Second  Assert");

        sa.assertEquals(response.getHeaders("Mime-type"),"Lorg.apache.http.Header;@539d019");

        client.close();
        response.close();
        sa.assertAll();
    }
}

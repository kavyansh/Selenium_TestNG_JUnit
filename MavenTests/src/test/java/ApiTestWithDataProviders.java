import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;


public class ApiTestWithDataProviders {



   private CloseableHttpResponse response;
    private CloseableHttpClient client;


   @BeforeMethod
    public void buildClient(){

    client = HttpClientBuilder.create().build();


   }

    @DataProvider
    public Object[][] endPointsRequiringAuthentication(){
       return new Object[][] {

               {"user"},
               {"user/followers"},
               {"notifications"}
               //etc
       };


    }

   @Test(dataProvider ="endPointsRequiringAuthentication" )
    public void userEndPointReturs401(String endPoint) throws IOException {

       response=client.execute(new HttpGet("https://api.github.com/"+endPoint));

       Assert.assertEquals(response.getStatusLine().getStatusCode(),401);


   }

   @AfterMethod
    public void cleanup() throws IOException {

       client.close();
       response.close();

   }






}

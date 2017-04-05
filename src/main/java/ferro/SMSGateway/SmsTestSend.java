package ferro.SMSGateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class SmsTestSend {
	private String myPasscode = "proxyferro";
	private String myUsername = "niels.baltodano";
	private String toPhoneNumber = "942865558";
	private String myMessage = "JAVA8 ki ki ki";
	private String url = "";
	private HttpClient client;
	

	public void testSMS() throws ClientProtocolException, IOException, URISyntaxException{
    url="http://cloud.fowiz.com/api/message_http_api.php?username=".concat(this.myUsername).concat("&phonenumber=").concat(this.toPhoneNumber)
    	.concat("&message=").concat(this.myMessage).concat("&passcode=").concat(myPasscode);
    //+myUsername+"&phonenumber="+toPhoneNumber+"&message="+myMessage+"&passcode="+myPasscode";
    
    String urlStr = url;
    URL url= new URL(urlStr);
    URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
    
    urlStr=uri.toASCIIString();
    	    
    System.out.println(urlStr);
    client = new DefaultHttpClient();
    HttpGet request = new HttpGet(urlStr);
    HttpResponse response = client.execute(request);

    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

    String line = "";
    StringBuffer response1 = new StringBuffer();
    while ((line = rd.readLine()) != null) {
              response1.append(line);
    }   
   
    System.out.println(response1.toString());
    
    }
}

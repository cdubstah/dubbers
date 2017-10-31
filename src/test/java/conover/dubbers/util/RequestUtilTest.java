package conover.dubbers.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.junit.Test;
import com.conover.dubbers.util.NewsAPI;
import com.conover.dubbers.util.RequestUtil;


public class RequestUtilTest {

	@Test 
	public void requestTest() throws ClientProtocolException, IOException {
		JSONObject test = RequestUtil.getRequest(NewsAPI.getApi("!bloomberg"));
		assert test != null;
		assert test.has("articles");
	}
	
	@Test(expected = ClientProtocolException.class)
	public void nullUrl() throws ClientProtocolException, IOException {
		RequestUtil.getRequest(null);
	}
	
	@Test(expected = ClientProtocolException.class)
	public void blankURL() throws ClientProtocolException, IOException {
		RequestUtil.getRequest("");
	}
	
}

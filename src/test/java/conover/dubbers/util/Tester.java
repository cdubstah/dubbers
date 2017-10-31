package conover.dubbers.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.conover.dubbers.util.RequestUtil;

public class Tester {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		RequestUtil.getRequest("");
	}
}

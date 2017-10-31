package com.conover.dubbers.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class RequestUtil {

	public static JSONObject getRequest(String url) throws ClientProtocolException, IOException {
		if (url == null || url.equals("")) 
			throw new ClientProtocolException("URL should not be blank!");
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		client.close();
		return new JSONObject(result.toString());

	}
}

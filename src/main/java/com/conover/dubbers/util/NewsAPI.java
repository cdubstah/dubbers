package com.conover.dubbers.util;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class NewsAPI {
	private static final int DEFAULT_LIST_LENGTH = 5;

	public static final HashMap<String, String> news = initMap();

	public static String getApi(String key) {
		return news.get(key);
	}

	private static HashMap<String,String> initMap() {
		HashMap<String, String> apiMap = new HashMap<String, String>();
		apiMap.put("!bloomberg",
				"https://newsapi.org/v1/articles?source=bloomberg&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!aljazeera",
				"https://newsapi.org/v1/articles?source=al-jazeera-english&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!bbc",
				"https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!businessinsider",
				"https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!buzzfeed",
				"https://newsapi.org/v1/articles?source=buzzfeed&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!cnbc",
				"https://newsapi.org/v1/articles?source=cnbc&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!espn",
				"https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!techcrunch",
				"https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!reddit",
				"https://newsapi.org/v1/articles?source=reddit-r-all&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		apiMap.put("!fortune",
				"https://newsapi.org/v1/articles?source=fortune&sortBy=top&apiKey=39699dcd5c6e4422b069f5f0229822f0");
		return apiMap;
	}

	public static void newsResponse(MessageReceivedEvent event, String message) {
		String responseMessage = "Top Articles";
		event.getTextChannel().sendMessage(responseMessage).queue();
		try {
			JSONObject json = RequestUtil.getRequest(getApi(message));
			JSONArray articles = json.getJSONArray("articles");
			int listLength = DEFAULT_LIST_LENGTH;
			if (articles.length() < DEFAULT_LIST_LENGTH)
				listLength = articles.length();
			for (int i = 1; i <= listLength; i++) {
				event.getTextChannel().sendMessage(i + ". " + articles.getJSONObject(i - 1).getString("url")).queue();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

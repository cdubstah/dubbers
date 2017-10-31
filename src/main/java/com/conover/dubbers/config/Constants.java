package com.conover.dubbers.config;

import java.util.LinkedHashMap;

public class Constants {
	public static final String discordToken = "discord key goes here";
	public static final String wolframAppId = "wolfram app id goes here";

	public static final LinkedHashMap<String, String> commands = initMap();
	
	private static LinkedHashMap<String,String> initMap() {
		LinkedHashMap<String, String> cmds = new LinkedHashMap<String, String>();
		cmds.put("!bloomberg", "provide Bloomberg top articles");
		cmds.put("!aljazeera", "provide Al Jazeera English top articles");
		cmds.put("!bbc", "provide BBC top articles");
		cmds.put("!businessinsider", "provide Business Insider top articles");
		cmds.put("!buzzfeed", "provide Buzzfeed top articles");
		cmds.put("!cnbc", "provide CNBC top articles");
		cmds.put("!espn", "provide ESPN top articles");
		cmds.put("!techcrunch", "provide TechCrunch top articles");
		cmds.put("!reddit", "provide Reddit top posts");
		cmds.put("!fortune", "provide Fortune top articles");
		cmds.put("!wolfram", "query with Wolfram Alpha");
		return cmds;
	}
}

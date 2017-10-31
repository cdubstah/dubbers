package com.conover.dubbers.messanger;

import com.conover.dubbers.config.Constants;
import com.conover.dubbers.util.NewsAPI;
import com.conover.dubbers.util.WolframAPI;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageResponder extends ListenerAdapter {

	private static final int DEFAULT_WOLFRAM_LENGTH = 9;

	public void onMessageReceived(MessageReceivedEvent event) {
		String message = event.getMessage().getContent();
		if (NewsAPI.getApi(message) != null) {
			NewsAPI.newsResponse(event, message);
		} else if (message.startsWith("!wolfram")) {
			if (message.split(" ").length > 1) {
				WolframAPI.query(message.substring(DEFAULT_WOLFRAM_LENGTH), event);
			} else {
				event.getTextChannel().sendMessage("Please use !wolfram with a query after it").queue();
				event.getTextChannel().sendMessage("For example: !wolfram what is the population of france");
			}
		} else if (message.equals("!help")) {
			helpResponse(event);
		}

	}

	private static void helpResponse(MessageReceivedEvent event) {
		StringBuffer helpMessage = new StringBuffer();
		helpMessage.append("Here is a list of commands that Dubbers supports \n");
		Constants.commands.forEach((k, v) -> {
			helpMessage.append(k + "        " + v + "\n\n");
		});
		event.getTextChannel().sendMessage(helpMessage.toString()).queue();
	}

}

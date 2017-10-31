package com.conover.dubbers.util;

import com.conover.dubbers.config.Constants;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class WolframAPI {
	public static void query(String input, MessageReceivedEvent event) {
		StringBuffer result = new StringBuffer();
		WAEngine engine = new WAEngine();
		engine.setAppID(Constants.wolframAppId);
		WAQuery query = engine.createQuery();
		query.setInput(input);

		try {
			WAQueryResult queryResult = engine.performQuery(query);
			if (queryResult.isError()) {
				result.append("Query Error \n");
				result.append("Error Code: " + queryResult.getErrorCode() + "\n");
				result.append("Error Message: " + queryResult.getErrorMessage());
				event.getTextChannel().sendMessage(result.toString()).queue();
			} else if (!queryResult.isSuccess()) {
				event.getTextChannel().sendMessage("Query found no results").queue();
			} else {
				event.getTextChannel().sendMessage("Query successful \n").queue();
				for (WAPod pod : queryResult.getPods()) {
					if (!pod.isError()) {
						event.getTextChannel().sendMessage(pod.getTitle() + "\n").queue();
						event.getTextChannel().sendMessage("-------------------------- \n").queue();
						for (WASubpod subpod : pod.getSubpods())
							for (Object element : subpod.getContents())
								if (element instanceof WAImage) {
									File temp = new File("temp.jpg");
									URL url = null;
									try {
										url = new URL(((WAImage) element).getURL());
									} catch (MalformedURLException e) {
										e.printStackTrace();
									}
									try {
										FileUtils.copyURLToFile(url, temp);
										Message message = new MessageBuilder().append(" ").build();
										event.getTextChannel().sendFile(temp, message).queue();
									} catch (IOException e) {
										e.printStackTrace();
									}

								}
					}
				}
			}
		} catch (WAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
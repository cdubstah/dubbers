package com.conover.dubbers;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

import com.conover.dubbers.config.Constants;
import com.conover.dubbers.messanger.MessageResponder;

public class Connection {
	public static void main(String[] args) {
		JDA discord = null;

		try {
			discord = new JDABuilder(AccountType.BOT).setToken(Constants.discordToken).buildBlocking();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}

		discord.addEventListener(new MessageResponder());
	}
}

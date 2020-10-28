package com.weatherbot;

import com.weatherbot.bot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@SpringBootApplication
public class BotMainApp implements ApplicationRunner {
    private final Bot bot;

    @Autowired
    public BotMainApp(Bot bot) {
        this.bot = bot;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(BotMainApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(bot);
    }
}

package com.weatherbot.bot;

import com.weatherbot.model.Weather;
import com.weatherbot.service.impl.WeatherServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;

@Service
public class Bot extends TelegramLongPollingBot {
    private final WeatherServiceImpl service;

    @Autowired
    public Bot(WeatherServiceImpl service) {
        this.service = service;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId().toString();
        var from = update.getMessage().getFrom();
        var text = update.getMessage().getText();

        System.out.println(from);
        System.out.println(text);


        var weatherInfo = service.getByCityName(text);

        System.out.println(Arrays.toString(weatherInfo));

        if (weatherInfo[0].getValid_date() == null) {
            var incorrectInput = "Incorrect city name '" + text + "'";
            sendMessage(chatId, incorrectInput);
        } else {
            for (Weather weather : weatherInfo) {
                sendMessage(chatId, weather.toString());
            }
        }
    }

    @SneakyThrows
    public synchronized void sendMessage(String chatId, String message) {
        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        execute(sendMessage);
    }

    @Override
    public String getBotUsername() {
        return "RoN_Weather_bot";
    }

    @Override
    public String getBotToken() {
        return "1167610860:AAHSo9YkchOWW59eyVnBLnkexMW9QAka7Rs";
    }
}

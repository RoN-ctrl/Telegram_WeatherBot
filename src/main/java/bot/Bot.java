package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId().toString();
        var from = update.getMessage().getFrom();
        var text = update.getMessage().getText();

        System.out.println(from);
        System.out.println(text);

        sendMessage(chatId, text);
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

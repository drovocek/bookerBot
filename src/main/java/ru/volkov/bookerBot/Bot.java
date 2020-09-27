package ru.volkov.bookerBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.volkov.bookerBot.config.Config;

public class Bot extends TelegramLongPollingBot {
    private final String USERNAME = Config.getProperty(Config.USERNAME);
    private final String TOKEN = Config.getProperty(Config.TOKEN);

    private SendMessage sendTextMessage = new SendMessage();

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            System.out.println(update.getMessage().getText());
        }
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}

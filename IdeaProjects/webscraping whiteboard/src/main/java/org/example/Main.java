package org.example;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import io.quickchart.QuickChart;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        try {
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(new MyBot());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }


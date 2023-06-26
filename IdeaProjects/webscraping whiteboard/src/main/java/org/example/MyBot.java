package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class MyBot extends TelegramLongPollingBot {
    List<Object> countryAPILanguages;
    Integer biggestAPIRequest = 0;
    String countryAPIName = "Error";
    String countryAPIPopulation = "Error";
    String countryAPISubregion = "Error";
    String catApiText = "Retrieving a cat fact took me too long, please try something else";
    String jokeAPIText = "Couldn't find a funny joke";
    String numbersAPIText = "Invalid number error";
    String quotesAPIText = "Unreachable quote";
    String quotesAPIAuthor = "Unreachable author";
    HashMap<Long, Integer> userIDLevelHashMap = new HashMap<>();
    HashMap<String, Integer> userNameUserRequestCountHashMap = new HashMap<>();
    HashMap<String, Integer> APINameUseCount = new HashMap<String, Integer>(){{
        put("CatsAPI", 0);
        put("NumbersAPI", 0);
        put("QuotesAPI", 0);
        put("JokesAPI", 0);
        put("CountriesAPI", 0);
    }
    };
    Queue<String> lastTenMessages = new LinkedList<>();
    private int requestCounter = 0;
    private int uniqueUsersCounter = 0;
    SwingWindow swingWindow = new SwingWindow();
    @Override
    public String getBotUsername() {
        return "dapezziBot";
    }//what to search for in telegram
    @Override
    public String getBotToken() {
        return "6240019548:AAHYY1By1rSRfyAcT_SO_WEJOmVGvGlxVuc";//long bot token from the telegram bot that allows connection
    }
    @Override
    public void onUpdateReceived(Update update) {
        Date date = new Date();
        String usermessage;
        Long chatID;
        String userFirstName;
        if (update.hasCallbackQuery()) {
            chatID = update.getCallbackQuery().getMessage().getChatId();
            usermessage = update.getCallbackQuery().getData().toString();
            userFirstName = update.getCallbackQuery().getFrom().getFirstName();
        } else {
            chatID = update.getMessage().getChatId();
            userFirstName = update.getMessage().getFrom().getFirstName();
            usermessage = update.getMessage().getText();
        }
        addToLastTenMessages(usermessage + ". " + userFirstName + ". " + date+ "\n");
        swingWindow.lastTenMessagesPanel.message.setText("<html>last ten messages: "+lastTenMessagesString()+"</html>");
        if (!userNameUserRequestCountHashMap.containsKey(userFirstName)){
            userNameUserRequestCountHashMap.put(userFirstName, 0);
        }
        else {
            userNameUserRequestCountHashMap.put(userFirstName, userNameUserRequestCountHashMap.get(userFirstName) + 1);
        }
        System.out.println(userNameUserRequestCountHashMap);
        Integer level = this.userIDLevelHashMap.get(chatID);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        calculateMostActiveUser();
        if (level == null && update.getMessage().getText().equals("/start")) {
            uniqueUsersCounter++;
            swingWindow.userStatisticsPanel.getUniqueUsersText().setText("Amount of unique users: " + uniqueUsersCounter);
            sendMessage.setText("Hello, I am Ben's telegram bot, here are your options:");
            InlineKeyboardButton numberFacts = new InlineKeyboardButton();
            numberFacts.setText("Number Facts");
            numberFacts.setCallbackData("numbers");
            InlineKeyboardButton countryDetails = new InlineKeyboardButton();
            countryDetails.setText("Country details");
            countryDetails.setCallbackData("country");
            InlineKeyboardButton jokes = new InlineKeyboardButton();
            jokes.setText("Jokes");
            jokes.setCallbackData("joke");
            InlineKeyboardButton Quotes = new InlineKeyboardButton();
            Quotes.setText("Quotes");
            Quotes.setCallbackData("quote");
            InlineKeyboardButton catFacts = new InlineKeyboardButton();
            catFacts.setText("Cat facts");
            catFacts.setCallbackData("cat");
            List<InlineKeyboardButton> topRow = new ArrayList<>();
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Number facts") == 1) {
                topRow.add(numberFacts);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Cat facts") == 1) {
                topRow.add(catFacts);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Jokes") == 1) {
                topRow.add(jokes);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Quotes") == 1) {
                topRow.add(Quotes);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Country details") == 1) {
                topRow.add(countryDetails);
            }
            List<List<InlineKeyboardButton>> keyboard = List.of(topRow);
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            this.userIDLevelHashMap.put(chatID,1);
        } else if (level == 1) {
            String message = update.getCallbackQuery().getData();
            if (Objects.equals(message, "cat")) {
                catAPIRun();
                updateAPIRequestCounter();
                sleep();
                sendMessage.setText(catApiText);
                this.APINameUseCount.put("CatsAPI", APINameUseCount.get("CatsAPI") + 1);
                this.userIDLevelHashMap.put(chatID, 0);
            }
            if (Objects.equals(message, "joke")) {
                jokeAPIRun("");//to be fleshed out later.
                updateAPIRequestCounter();
                sleep();
                sendMessage.setText(jokeAPIText);
                this.APINameUseCount.put("JokesAPI", APINameUseCount.get("JokesAPI") + 1);
                this.userIDLevelHashMap.put(chatID, 0);
            }
            if (Objects.equals(message, "numbers")){
                sendMessage.setText("Pick a number you want a fact for");
                this.userIDLevelHashMap.put(chatID, 2);
            }
            if (Objects.equals(message, "quote")){
                quotesAPIRun();
                updateAPIRequestCounter();
                sleep();
                sendMessage.setText(quotesAPIText);
                this.APINameUseCount.put("QuotesAPI", APINameUseCount.get("QuotesAPI") + 1);
                this.userIDLevelHashMap.put(chatID, 0);
            }
            if (Objects.equals(message, "country")){
                System.out.println(1);
                sendMessage.setText("Please message me a country code");
                this.userIDLevelHashMap.put(chatID, 3);
            }
        }
        else if (level == 2){
            numbersAPIRun(update.getMessage().getText());
            updateAPIRequestCounter();
            sleep();
            sendMessage.setText(numbersAPIText);
            this.APINameUseCount.put("NumbersAPI", APINameUseCount.get("NumbersAPI") + 1);
            this.userIDLevelHashMap.put(chatID, 0);
        } else if (level == 3) {
            countriesAPIRun(update.getMessage().getText());
            updateAPIRequestCounter();
            sleep();
            sendMessage.setText("You chose "+ countryAPIName +". Now choose your desired information:");
            InlineKeyboardButton population = new InlineKeyboardButton();
            population.setText("population count");
            population.setCallbackData("population");
            InlineKeyboardButton location = new InlineKeyboardButton();
            location.setText("location");
            location.setCallbackData("subregion");
            InlineKeyboardButton languages = new InlineKeyboardButton();
            languages.setText("spoken languages");
            languages.setCallbackData("language");
            List<InlineKeyboardButton> topRow = List.of(languages,location,population);
            List<List<InlineKeyboardButton>> keyboard = List.of(topRow);
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
            markup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(markup);
            this.userIDLevelHashMap.put(chatID, 4);
        }
        else if (level == 4) {
            String message = update.getCallbackQuery().getData();
            if (Objects.equals(message,"population")){
                sendMessage.setText(countryAPIPopulation);
            }
            if (Objects.equals(message,"language")){
                sendMessage.setText(countryAPILanguages.toString());
            }
            if (Objects.equals(message,"subregion")){
                sendMessage.setText(countryAPISubregion);
            }
            this.APINameUseCount.put("CountriesAPI", APINameUseCount.get("CountriesAPI") + 1);
            this.userIDLevelHashMap.put(chatID, 0);
        }
        else if (level == 0 && update.getMessage().getText().equals("/restart")){
            sendMessage.setText("Tip: remember you can change your options in the swing gui. here are your current options");
            InlineKeyboardButton numberFacts = new InlineKeyboardButton();
            numberFacts.setText("Number Facts");
            numberFacts.setCallbackData("numbers");
            InlineKeyboardButton countryDetails = new InlineKeyboardButton();
            countryDetails.setText("Country details");
            countryDetails.setCallbackData("country");
            InlineKeyboardButton jokes = new InlineKeyboardButton();
            jokes.setText("Jokes");
            jokes.setCallbackData("joke");
            InlineKeyboardButton Quotes = new InlineKeyboardButton();
            Quotes.setText("Quotes");
            Quotes.setCallbackData("quote");
            InlineKeyboardButton catFacts = new InlineKeyboardButton();
            catFacts.setText("Cat facts");
            catFacts.setCallbackData("cat");
            List<InlineKeyboardButton> topRow = new ArrayList<>();
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Number facts") == 1) {
                topRow.add(numberFacts);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Cat facts") == 1) {
                topRow.add(catFacts);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Jokes") == 1) {
                topRow.add(jokes);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Quotes") == 1) {
                topRow.add(Quotes);
            }
            if (swingWindow.controlPanel.savedCheckboxSelections.get("Country details") == 1) {
                topRow.add(countryDetails);
            }
            List<List<InlineKeyboardButton>> keyboard = List.of(topRow);
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            this.userIDLevelHashMap.put(chatID,1);
        }
        send(sendMessage);
        calculateMostPopularAPI();
    }
    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void catAPIRun() {
        new Thread(() -> {
            try {
                HttpResponse<String> response = Unirest.get("https://catfact.ninja/fact?max_length=140").asString();
                System.out.println(response.getStatus());//http connection code
                ObjectMapper objectMapper = new ObjectMapper();
                CatModel catModel = objectMapper.readValue(response.getBody(), CatModel.class);
                if (catModel != null) {
                 this.catApiText = catModel.getFact();
                }
            } catch (Exception e){
                try {
                    throw new Exception("Error");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }).start();
    }
    public void jokeAPIRun(String x){
        new Thread(() -> {
            try {
                HttpResponse<String> response = Unirest.get("https://v2.jokeapi.dev/joke/Any" + x).asString();
                System.out.println(response.getStatus());//http connection code
                ObjectMapper objectMapper = new ObjectMapper();
                JokeModel jokeModel = objectMapper.readValue(response.getBody(), JokeModel.class);
                if (jokeModel.getType().equals("twopart") ){
                    this.jokeAPIText = jokeModel.getSetup()+ " " + jokeModel.getDelivery();
                }
                else {
                    this.jokeAPIText = jokeModel.getJoke();
                }
            } catch (Exception e){
                try {
                    throw new Exception("Error");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }).start();
    }
    public void numbersAPIRun(String x){
        new Thread(() ->{
            try {
                HttpResponse<String> response = Unirest.get("http://numbersapi.com/" + x).asString();
                if (response.getStatus() == 200){
                    this.numbersAPIText = response.getBody();
                }
            } catch (Exception e){
                try {
                    throw new Exception("Error");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }).start();
    }
    public void quotesAPIRun(){
        new Thread(() ->{
            try {
                HttpResponse<String> response = Unirest.get("https://api.quotable.io/random").asString();
                System.out.println(response.getStatus());//http connection code
                ObjectMapper objectMapper = new ObjectMapper();
                QuotesModel quotesModel = objectMapper.readValue(response.getBody(), QuotesModel.class);
                this.quotesAPIAuthor = quotesModel.getAuthor();
                this.quotesAPIText = quotesModel.getContent();
            }catch (Exception e){
                System.out.println("Error");
            }
        }).start();
    }
    public void countriesAPIRun(String name){
        new Thread(() ->{
            try {
                HttpResponse<String> response = Unirest.get("https://restcountries.com/v2/alpha/" + name).asString();
                ObjectMapper objectMapper = new ObjectMapper();
                CountryModel countryModel = objectMapper.readValue(response.getBody(), CountryModel.class);
                System.out.println(response.getBody());
                this.countryAPILanguages = countryModel.getLanguages();
                this.countryAPIPopulation = countryModel.getPopulation();
                this.countryAPIName = countryModel.getName();
                this.countryAPISubregion = countryModel.getSubregion();
            } catch (Exception e){
                System.out.println("error");
            }
        }).start();
    }
    public void updateAPIRequestCounter(){
        this.requestCounter++;
        swingWindow.userStatisticsPanel.getRequestsText().setText("Amount of API requests across all users: "+requestCounter);
    }
    public void calculateMostActiveUser(){
        int least = 0;
        String most = "";
        for (String username : userNameUserRequestCountHashMap.keySet()){
            if (userNameUserRequestCountHashMap.get(username) > least){
                least = userNameUserRequestCountHashMap.get(username);
            }
        }
        for (String username : userNameUserRequestCountHashMap.keySet()){
            if (userNameUserRequestCountHashMap.get(username) == least){
                most = username;
            }
        }
        swingWindow.userStatisticsPanel.getMostActiveUserText().setText("Most active user: "+ most);
    }
    public void calculateMostPopularAPI(){
        System.out.println(APINameUseCount);
        for (String api: this.APINameUseCount.keySet()){
            if (APINameUseCount.get(api) > biggestAPIRequest){
                biggestAPIRequest = APINameUseCount.get(api);
            }
        }
        for (String api : this.APINameUseCount.keySet()){
            if (APINameUseCount.get(api).equals(biggestAPIRequest)){
                swingWindow.userStatisticsPanel.getMostPopularAPI().setText("Most popular API: "+ api);
            }
        }
    }
    public void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToLastTenMessages(String message){
        if (this.lastTenMessages.size() > 9){
            lastTenMessages.remove();
            lastTenMessages.add(message);
        }
        else {
            lastTenMessages.add(message);
        }
    }
    public String lastTenMessagesString(){
        StringBuilder x = new StringBuilder();
        for (String message : lastTenMessages){
            x.append(message);
            x.append("<br><br>");
        }
        return x.toString();
    }
}

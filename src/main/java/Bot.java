import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {


    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "Помощь": {
                    System.out.println("help");
                    Help(message);
                }
                break;
                case "Важное": {
                    System.out.println("Settings");
                    Settings(message);
                }
                break;
                case "Новости": {
                    System.out.println("News");
                    News(message);
                }
                break;
                case "Admin Panel": {
                    Admin(message);
                    System.out.println("Admn Panel");
                }
                break;
                default: {
                    sendMsg(message, "Я нахожусь ещё в разработке и поэтому выполняю нге все функции! Проверте правильность набора задачи!!!");
                }
                break;
            }
        }
    }

    public void Admin(Message message) {
        String pas = "Z2";
        String Log = "Z";
        sendMsg(message, "Enter Admin pas");

        if (GetPas(message) == "Z2") {
            sendMsg(message, "Вы находитесь в админской панеле");
        }
    }

    public String GetPas(Message message) {
        String answeruserpas = message.getText();
        return answeruserpas;
    }

    public void Help(Message message) {
        String answer = "Данный бот не совершенен, могут быть неполадки. Если такие есть обращайтесь в Валентине Зиньковской со своей проблемой, и в скором времени мы её исправим";
        sendMsg(message, answer);
    }

    public void Settings(Message message) {
        String answer = "В данном боте пока не реализована возможность записываться на стрижку. В ближайшее будуюшее мы это реализуем. ";
        sendMsg(message, answer);
    }

    public void News(Message message) {
        String answer = "В ближайшее будуюшее мы это реализуем. ";
        sendMsg(message, answer);
    }


    public void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        replyKeyboardMarkup.setSelective(true);

        replyKeyboardMarkup.setResizeKeyboard(true);

        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();


        keyboardFirstRow.add(new KeyboardButton("Помощь"));
        keyboardFirstRow.add(new KeyboardButton("Важное"));
        keyboardSecondRow.add(new KeyboardButton("Новости"));
        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);


    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        //sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId()); // выводить ответ на твое сообщение
        sendMessage.setText(text);
        setButton(sendMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "Любимые Клиенты";
    }

    @Override
    public String getBotToken() {
        return "731433456:AAHiG4zmCqTL-qCoTLCBkCpZ-VCxGhYIzd4";
    }
}

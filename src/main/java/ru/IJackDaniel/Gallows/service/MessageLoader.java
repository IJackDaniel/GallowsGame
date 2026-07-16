package ru.IJackDaniel.Gallows.service;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageLoader {
    private static MessageLoader instance;
    private ResourceBundle bundle;

    private MessageLoader() {
        loadMessages();
    }

    public static MessageLoader getInstance() {
        if (instance == null) {
            instance = new MessageLoader();
        }
        return instance;
    }

    private void loadMessages() {
        try {
            bundle = ResourceBundle.getBundle("messages");
        } catch (MissingResourceException exception) {
            System.err.println("Ошибка чтения файла messages.properties");
        }
    }

    public String getMessage(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return "???" + key + "???";
        }
    }
}

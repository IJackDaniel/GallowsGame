package ru.IJackDaniel.Gallows.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WordsFromFileReader {
    private static WordsFromFileReader instance;
    private final MessageLoader messageLoader = MessageLoader.getInstance();

    private WordsFromFileReader() {
    }

    public static WordsFromFileReader getInstance() {
        if (instance == null) {
            instance = new WordsFromFileReader();
        }
        return instance;
    }

    public List<String> readWords(String filePath) {

        List<String> words = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            System.err.println(messageLoader.getMessage("error.FileNotFound"));
            return words;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException exception) {
            System.err.println(messageLoader.getMessage("error.FileReadingError"));
        }
        return words;
    }
}

package ru.IJackDaniel.Gallows.util;

import ru.IJackDaniel.Gallows.model.Dictionary;

import java.util.Random;

public class DictionaryUtil {

    private final Dictionary words;

    public DictionaryUtil(Dictionary words) {
        this.words = words;
    }

    public boolean isDictionaryReady() {
        return (this.words != null && !this.words.isEmpty());
    }

    public String getRandomWord() {
        Random random = new Random();
        return this.words.getByIndex(random.nextInt(this.words.length()));
    }
}

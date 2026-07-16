package ru.IJackDaniel.Gallows.model;

import java.util.List;
import java.util.Random;

public class Dictionary {

    private final List<String> words;

    public Dictionary(List<String> words) {
        this.words = words;
    }

    // Некорректный индекс никогда не подаётся
    public String getByIndex(int index) {
        return this.words.get(index);
    }

    public int length() {
        return this.words.size();
    }

    public boolean isEmpty() {
        return this.words.isEmpty();
    }
}

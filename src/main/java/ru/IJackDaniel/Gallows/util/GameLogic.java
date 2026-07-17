package ru.IJackDaniel.Gallows.util;

import java.util.List;

public class GameLogic {

    public boolean checkWin(String word, List<Character> userInput) {
        return countCollision(word, userInput) == word.length();
    }

    public int countCollision(String word, List<Character> userInput) {
        int collision = 0;
        for (Character character : word.toCharArray()) {
            if (userInput.contains(character)) {
                collision++;
            }
        }
        return collision;
    }

}

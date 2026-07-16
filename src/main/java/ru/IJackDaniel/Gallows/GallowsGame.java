package ru.IJackDaniel.Gallows;

import ru.IJackDaniel.Gallows.service.WordsFromFileReader;
import ru.IJackDaniel.Gallows.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GallowsGame {

    private final int COUNT_TRIES = 10;

    private List<String> words;
    private View gallowsView;

    public GallowsGame(String filePath) {
        gallowsView = new View();

        WordsFromFileReader reader = WordsFromFileReader.getInstance();
        this.words = reader.readWords(filePath);
    }

    private boolean isDictionaryReady() {
        return (this.words != null && !this.words.isEmpty());
    }

    // Перенеси потом в отдельный класс и словарь, и метод
    private String getRandomWord() {
        Random random = new Random();
        return this.words.get(random.nextInt(this.words.size()));
    }

    public void runGame() {
        boolean readiness = this.isDictionaryReady();
        gallowsView.printGameReadiness(readiness);
        if (!this.isDictionaryReady()) {
            return;
        }

        String word = this.getRandomWord();
        boolean guess = false;
        boolean isGameFinish = false;
        int countTries = COUNT_TRIES;
        int collision = 0;

        List<Character> userInput = new ArrayList<>();
        System.out.println("Отладочный вывод слова: " + word);
        do {
            gallowsView.printCountTries(countTries);
            gallowsView.showWordWithEEE(word, userInput);

            Character inputCharacter = gallowsView.getUserInput();
            userInput.add(inputCharacter);

            int currentCollision = countCollision(word, userInput);

            if (currentCollision != collision + 1) {
                countTries--;
            }
            collision = currentCollision;

            if (this.checkWin(word, userInput)) {
                isGameFinish = true;
                guess = true;
            }
            if (countTries <= 0) {
                isGameFinish = true;
            }

        } while (!isGameFinish);

        gallowsView.printGameResult(guess, word);

    }

    private boolean checkWin(String word, List<Character> userInput) {
        return countCollision(word, userInput) == word.length();
    }

    private int countCollision(String word, List<Character> userInput) {
        int collision = 0;
        for (Character character : word.toCharArray()) {
            if (userInput.contains(character)) {
                collision++;
            }
        }
        return collision;
    }
}

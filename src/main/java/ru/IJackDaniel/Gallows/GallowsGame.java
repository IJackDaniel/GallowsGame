package ru.IJackDaniel.Gallows;

import ru.IJackDaniel.Gallows.service.WordsFromFileReader;
import ru.IJackDaniel.Gallows.util.DictionaryUtil;
import ru.IJackDaniel.Gallows.view.View;
import ru.IJackDaniel.Gallows.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class GallowsGame {

    private final int COUNT_ATTEMPTS = 10;

    private final Dictionary words;
    private final DictionaryUtil dictionaryUtil;
    private final View gallowsView;

    public GallowsGame(String filePath) {
        gallowsView = new View();

        WordsFromFileReader reader = WordsFromFileReader.getInstance();
        this.words = new Dictionary(reader.readWords(filePath));
        this.dictionaryUtil = new DictionaryUtil(this.words);
    }



    public void runGame() {
        boolean readiness = this.dictionaryUtil.isDictionaryReady();
        gallowsView.printGameReadiness(readiness);
        if (!readiness) {
            return;
        }

        String guessedWord = this.dictionaryUtil.getRandomWord();
        boolean isGuessed = false;
        boolean isGameFinish = false;
        int countAttempts = COUNT_ATTEMPTS;
        int countCollision = 0;
        List<Character> userInput = new ArrayList<>();

        System.out.println("Отладочный вывод слова: " + guessedWord);

        do {
            gallowsView.printCountAttempts(countAttempts);
            gallowsView.showWordWithOmissions(guessedWord, userInput);

            Character inputCharacter = gallowsView.getUserInput();
            userInput.add(inputCharacter);

            int currentCollision = countCollision(guessedWord, userInput);

            if (currentCollision <= countCollision) {
                countAttempts--;
            }
            countCollision = currentCollision;
            if (this.checkWin(guessedWord, userInput)) {
                isGameFinish = true;
                isGuessed = true;
            }
            if (countAttempts <= 0) {
                isGameFinish = true;
            }

        } while (!isGameFinish);

        gallowsView.printGameResult(isGuessed, guessedWord);

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

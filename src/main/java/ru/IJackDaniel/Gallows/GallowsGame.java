package ru.IJackDaniel.Gallows;

import ru.IJackDaniel.Gallows.service.WordsFromFileReader;
import ru.IJackDaniel.Gallows.util.DictionaryUtil;
import ru.IJackDaniel.Gallows.util.GameLogic;
import ru.IJackDaniel.Gallows.view.View;
import ru.IJackDaniel.Gallows.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class GallowsGame {

    private final int COUNT_ATTEMPTS = 7;

    private final DictionaryUtil dictionaryUtil;
    private final GameLogic gameLogic;
    private final View gallowsView;


    public GallowsGame(String filePath) {
        WordsFromFileReader reader = WordsFromFileReader.getInstance();
        Dictionary words = new Dictionary(reader.readWords(filePath));
        this.dictionaryUtil = new DictionaryUtil(words);

        this.gallowsView = new View();
        this.gameLogic = new GameLogic();
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

        do {
            gallowsView.printCountAttempts(countAttempts);
            gallowsView.showWordWithOmissions(guessedWord, userInput);

            Character inputCharacter = gallowsView.getUserInput();
            userInput.add(inputCharacter);

            int currentCollision = this.gameLogic.countCollision(guessedWord, userInput);

            if (currentCollision <= countCollision) {
                countAttempts--;
            }
            countCollision = currentCollision;
            if (this.gameLogic.checkWin(guessedWord, userInput)) {
                isGameFinish = true;
                isGuessed = true;
            }
            if (countAttempts <= 0) {
                isGameFinish = true;
            }

        } while (!isGameFinish);

        gallowsView.printGameResult(isGuessed, guessedWord);

    }
}

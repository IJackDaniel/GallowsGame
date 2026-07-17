package ru.IJackDaniel.Gallows.view;

import ru.IJackDaniel.Gallows.service.MessageLoader;

import java.util.List;
import java.util.Scanner;

public class View {

    private final int FIRST_INDEX = 0;
    private final int CHARACTER_LENGTH = 1;

    private final MessageLoader messages;
    private final Scanner scanner;

    public View() {
        this.messages = MessageLoader.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void printGameReadiness(boolean isGameReady) {
        String key;
        if (isGameReady) {
            key = "game.readiness.ready";
        } else {
            key = "game.readiness.notReady";
        }
        System.out.println(this.messages.getMessage(key));

    }

    public void printGameResult(boolean isWordGuessed, String word) {
        String key;
        if (isWordGuessed) {
            key = "game.message.win";
        } else {
            key = "game.message.lose";
        }
        System.out.println(this.messages.getMessage(key) + word);
    }

    public void printCountAttempts(int countAttempts) {
        System.out.println(this.messages.getMessage("game.message.countAttempts") + countAttempts);
        // Код для вывода виселицы. Как позитивно)
        String key = "game.message.gallows.attemptsLeft" + countAttempts;
        System.out.println(this.messages.getMessage(key));
    }

    public void showWordWithOmissions(String guessedWord, List<Character> userInput) {
        System.out.print(this.messages.getMessage("game.message.showWord"));
        for (char character : guessedWord.toCharArray()) {
            if (userInput.contains(character)) {
                System.out.print(character);
            } else {
                System.out.print(this.messages.getMessage("game.message.omissionsSymbol"));
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    public Character getUserInput() {
        String input;
        do {
            System.out.print(messages.getMessage("game.message.inputCharacter"));
            input = scanner.nextLine();
        } while (input.length() != CHARACTER_LENGTH || !Character.isLetter(input.charAt(FIRST_INDEX)));
        return input.charAt(FIRST_INDEX);
    }
}

package ru.IJackDaniel.Gallows;


public class Main {

    private static final String dataFileName = "RussianWords.txt";

    public static void main( String[] args ) {
        GallowsGame game = new GallowsGame(Main.dataFileName);
//        game.printWords();
        game.runGame();
    }

}

package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;

public class AbilityDeck implements Deck {
    private Stack<RRCard> deck;
    private ArrayList<RRCard> deckList;
    private String ability;
    private File file;

    /**
     * Creates a deck of cards
     * @param fileName File needs to be placed in cardDocs directory
     */
    public AbilityDeck(String fileName){
        fileName = ".\\src\\main\\java\\inf112\\skeleton\\app\\cards\\cardDocs\\"+fileName;
        file = new File(fileName);
        //file =new File(fileName);
        deck  = new Stack<>();
        deckList = new ArrayList<>();
        createDeck();
    }

    @Override
    public void reset() {
        deck.clear();
        createDeck();
    }

    @Override
    public void createDeck() {
        try{
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                deckList.add(new AbilityCard(reader.nextLine()));
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deckList);
        deck.addAll(deckList);
        deckList.clear();
    }



    @Override
    public ArrayList<RRCard> deal(int health) {
        ArrayList<RRCard> playerDeck = new ArrayList<>();
        playerDeck.add(deck.pop());
        return playerDeck;
    }

}

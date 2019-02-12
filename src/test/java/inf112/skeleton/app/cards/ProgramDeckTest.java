package inf112.skeleton.app.cards;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ProgramDeckTest {
    private String filename = "testProgram.txt";

    @Test
    public void sizeTest() {
        IDeck deck = new ProgramDeck(filename);
        ArrayList<RRCard> testDeck;

        testDeck=deck.deal(1);
        assertEquals(1,testDeck.size());
    }

    @Test
    public void getInformationOnElement() {
        IDeck deck = new ProgramDeck(filename);
        ArrayList<RRCard> testDeck;
        //ProgramCard card= new ProgramCard(820,"Move 3");
        ProgramCard card;

        int priority=820;
        Program program = Program.MOVE3;

        testDeck=deck.deal(1);
        card=(ProgramCard)testDeck.get(0);

        assertEquals(priority,card.getPriority());
        assertSame(program,card.getMove());


    }
}
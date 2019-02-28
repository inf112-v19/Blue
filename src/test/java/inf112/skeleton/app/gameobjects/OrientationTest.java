package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrientationTest {


    @Test
    public void rotate() {
        Orientation o1 = Orientation.FACING_NORTH;
        Orientation o2 = Orientation.FACING_SOUTH;
        Orientation o3 = Orientation.FACING_EAST;

        o1 = o1.rotate(Program.U);
        o2 = o2.rotate(Program.RIGHT);
        o3 = o3.rotate(Program.MOVE3);

        assertEquals(Orientation.FACING_SOUTH,o1);
        assertEquals(Orientation.FACING_WEST,o2);
        assertEquals(Orientation.FACING_EAST,o3);
    }

    @Test
    public void rotate1() {
        Orientation o1 = Orientation.FACING_NORTH;
        Orientation o2 = Orientation.FACING_WEST;
        Orientation o3 = Orientation.FACING_EAST;

        o1 = Orientation.rotate(o1,Program.LEFT);
        o2 = Orientation.rotate(o2,Program.U);
        o3 = Orientation.rotate(o3,Program.MOVE3);

        assertEquals(Orientation.FACING_WEST,o1);
        assertEquals(Orientation.FACING_EAST,o2);
        assertEquals(Orientation.FACING_EAST,o3);
    }
}
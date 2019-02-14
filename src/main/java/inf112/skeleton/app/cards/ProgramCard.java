package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Program card lass that contains a priority and movement.
  */

public class ProgramCard implements RRCard {
    private int priority;
    private Program move;
    private Sprite sprite;
    private Texture texture;

    /**
     *
     * @param priority is the card priority for movement
     * @param move is the directioon magnitude or change
     */
    public ProgramCard(int priority, String move) {
        this.priority = priority;
        this.move=translateMove(move);
    }

    /**
     * Only used for testing
     * @param priority priorty of card
     * @param move move the card is to have
     */
    public ProgramCard(int priority,Program move){
        this.priority = priority;
        this.move=move;
    }

    /** Get priority int
     */
    public int getPriority() {
        return this.priority;
    }

    /** Get Program enum for card.
      */
    public Program getMove() {
        return this.move;
    }

    /**
     * Translate string to Program
     * @param s description og movement to be translated.
     */
    private Program translateMove (String s){
        switch (s) {
            case "U Turn":
                return Program.U;
            case "Rotate Left":
                return Program.LEFT;
            case "Rotate Right":
                return Program.RIGHT;
            case "Back Up":
                return Program.BACK;
            case "Move 1":
                return Program.MOVE1;
            case "Move 2":
                return Program.MOVE2;
            case "Move 3":
                return Program.MOVE3;
            default:
                throw new IllegalArgumentException("Invalid move: " + s);
        }
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return Integer.compare(getPriority(), ((ProgramCard) o).getPriority());
    }
}

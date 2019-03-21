package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;

public enum Orientation {
    FACING_NORTH,
    FACING_WEST,
    FACING_SOUTH,
    FACING_EAST,
    HORIZONTAL,
    VERTICAL,
    NONE;

    /**
     * Rotates the orientation from the rartionen given from ProgramCard.
     * @param rotation Program with orientation wanted.
     * @return New orientation after rotation
     */
    public Orientation rotate(Program rotation){
        switch (this) {
            case FACING_NORTH:
                switch (rotation) {
                    case RIGHT:
                        return FACING_EAST;
                    case LEFT:
                        return FACING_WEST;
                    case U:
                        return FACING_SOUTH;
                    default: return this;
                }
            case FACING_SOUTH:
                switch (rotation) {
                    case RIGHT:
                        return FACING_WEST;
                    case LEFT:
                        return FACING_EAST;
                    case U:
                        return FACING_NORTH;
                    default: return this;
                }
            case FACING_EAST:
                switch (rotation) {
                    case RIGHT:
                        return FACING_SOUTH;
                    case LEFT:
                        return FACING_NORTH;
                    case U:
                        return FACING_WEST;
                    default: return this;
                }
            case FACING_WEST:
                switch (rotation) {
                    case RIGHT:
                        return FACING_NORTH;
                    case LEFT:
                        return FACING_SOUTH;
                    case U:
                        return FACING_EAST;
                    default: return this;
                }
            default: return this;
        }

    }

}

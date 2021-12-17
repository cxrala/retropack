public enum Movement {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public Movement opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
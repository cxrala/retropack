package game.retropack.an639;

public enum Movement{
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

    public static Point nextPoint(Movement currentMovement, Point point) {
        Point nextPoint =
                switch (currentMovement) {
                    case UP -> new Point(point.getX(), point.getY() - 1);
                    case DOWN -> new Point(point.getX(), point.getY() + 1);
                    case LEFT -> new Point(point.getX() - 1, point.getY());
                    case RIGHT -> new Point(point.getX() + 1, point.getY());
                };
        return nextPoint;
    }
}
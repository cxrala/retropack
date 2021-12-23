package game.retropack.an639;

public enum Movement2D {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public Movement2D opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }

    public static Point nextPoint(Movement2D currentMovement2D, Point point) {
        Point nextPoint =
                switch (currentMovement2D) {
                    case UP -> new Point(point.getX(), point.getY() - 1);
                    case DOWN -> new Point(point.getX(), point.getY() + 1);
                    case LEFT -> new Point(point.getX() - 1, point.getY());
                    case RIGHT -> new Point(point.getX() + 1, point.getY());
                };
        return nextPoint;
    }
}
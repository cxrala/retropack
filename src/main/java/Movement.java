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

    public static Point getNewHead(Movement currentMovement, Point head) {
        Point newHead =
                switch (currentMovement) {
                    case UP -> new Point(head.getX(), head.getY() - 1);
                    case DOWN -> new Point(head.getX(), head.getY() + 1);
                    case LEFT -> new Point(head.getX() - 1, head.getY());
                    case RIGHT -> new Point(head.getX() + 1, head.getY());
                };
        return newHead;
    }
}
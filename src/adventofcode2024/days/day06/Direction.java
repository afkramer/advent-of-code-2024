package adventofcode2024.days.day06;

import java.util.function.UnaryOperator;

public enum Direction {
    NORTH(x -> x - 1, y -> y),
    EAST(x -> x, y -> y + 1),
    SOUTH(x -> x + 1, y -> y),
    WEST(x -> x, y -> y - 1);

    private UnaryOperator<Integer> xMovement;
    private UnaryOperator<Integer> yMovement;

    private Direction(UnaryOperator<Integer> xMovement,
            UnaryOperator<Integer> yMovement) {
        this.xMovement = xMovement;
        this.yMovement = yMovement;
    }

    public int moveX(int currentX) {
        return this.xMovement.apply(currentX);
    }

    public int moveY(int currentY) {
        return this.yMovement.apply(currentY);
    }

    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
}

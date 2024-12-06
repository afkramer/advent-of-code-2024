package adventofcode2024.days.day06;

import java.util.ArrayList;
import java.util.List;

import adventofcode2024.Utils;
import adventofcode2024.days.Day;

public class Day06 implements Day {
    private List<Coordinate> originalRoute;

    @Override
    public void part1(String input) {
        List<String> inputLines = Utils.readData(input);
        int minX = 0;
        int minY = 0;
        int maxX = inputLines.size() - 1;
        int maxY = inputLines.get(minX).length() - 1;
        int currX = 0;
        int currY = 0;
        Direction currentDirection = Direction.NORTH;
        List<Coordinate> visitedCoordinates = new ArrayList<>();

        // determine where guard is now
        for (int i = 0; i <= maxX; i++) {
            if (inputLines.get(i).contains("^")) {
                currX = i;
                currY = inputLines.get(i).indexOf("^");
            }
        }

        visitedCoordinates.add(new Coordinate(currX, currY, 1));
        // System.out.println(String.format("Add coordinate at: %d, %d", currX, currY));
        boolean nextCoordInBounds = true;
        // Follow path until x or y is beyond grid
        while (nextCoordInBounds) {

            int nextX = currentDirection.moveX(currX);
            int nextY = currentDirection.moveY(currY);

            nextCoordInBounds = nextX >= minX && nextX <= maxX && nextY >= minY && nextY <= maxY;
            if (nextCoordInBounds && inputLines.get(nextX).charAt(nextY) == '#') {
                currentDirection = currentDirection.turnRight();
            } else if (nextCoordInBounds) {
                currX = currentDirection.moveX(currX);
                currY = currentDirection.moveY(currY);

                boolean alreadyVisited = visitedCoordinates.contains(new Coordinate(currX, currY, 1));
                if (!alreadyVisited) {
                    visitedCoordinates.add(new Coordinate(currX, currY, 1));
                    // System.out.println(String.format("Add coordinate at: %d, %d", currX, currY));
                }
            }

        }

        this.originalRoute = visitedCoordinates;
        System.out.println(String.format("Day 6 part 1: %d", visitedCoordinates.size()));
    }

    @Override
    public void part2(String input) {
        int startingX = 0;
        int startingY = 0;
        List<String> inputLines = Utils.readData(input);
        int minX = 0;
        int minY = 0;
        int maxX = inputLines.size() - 1;
        int maxY = inputLines.get(minX).length() - 1;
        int currX = 0;
        int currY = 0;
        Direction currentDirection = Direction.NORTH;
        List<Coordinate> visitedCoordinates;
        int numberOfPossibleNewGrids = 0;

        // determine where guard is now
        for (int i = 0; i <= maxX; i++) {
            if (inputLines.get(i).contains("^")) {
                startingX = i;
                startingY = inputLines.get(i).indexOf("^");
            }
        }

        // Use the resulting steps of part one to place different obstacles
        for (Coordinate coordinate : this.originalRoute) {
            // System.out.println(String.format("Placing new obstacle at %d, %d",
            // coordinate.x(), coordinate.y()));
            if (coordinate.x() == startingX && coordinate.y() == startingY) {
                continue;
            }

            List<String> changedGrid = Utils.copyList(inputLines);
            // System.out.println(String.format("Line %d at %d before change: %s",
            // coordinate.x(), coordinate.y(),
            // changedGrid.get(coordinate.x())));
            StringBuilder newString = new StringBuilder();
            newString.append(changedGrid.get(coordinate.x()).substring(0, coordinate.y()));
            newString.append("#");
            newString.append(changedGrid.get(coordinate.x()).substring(coordinate.y() + 1));
            changedGrid.set(coordinate.x(), newString.toString());
            // System.out.println(String.format("Line %d at %d after change: %s",
            // coordinate.x(), coordinate.y(),
            // changedGrid.get(coordinate.x())));
            // System.out.println("Now grid looks like: ");
            // Utils.printLists(changedGrid);

            currX = startingX;
            currY = startingY;
            currentDirection = Direction.NORTH;

            visitedCoordinates = new ArrayList<>();
            visitedCoordinates.add(new Coordinate(currX, currY, 1));
            boolean nextCoordInBounds = true;
            int oldNumVisitedCoordinates;
            int numTimesNoNewVisits = 0;
            // Follow path until x or y is beyond grid
            while (nextCoordInBounds && numTimesNoNewVisits < (maxX * maxY)) {
                oldNumVisitedCoordinates = visitedCoordinates.size();
                int nextX = currentDirection.moveX(currX);
                int nextY = currentDirection.moveY(currY);

                nextCoordInBounds = nextX >= minX && nextX <= maxX && nextY >= minY && nextY <= maxY;
                if (nextCoordInBounds && changedGrid.get(nextX).charAt(nextY) == '#') {
                    // System.out.println(
                    // String.format("Symbol at %d, %d: %s", nextX, nextY,
                    // changedGrid.get(nextX).charAt(nextY)));
                    currentDirection = currentDirection.turnRight();
                } else if (nextCoordInBounds) {
                    currX = currentDirection.moveX(currX);
                    currY = currentDirection.moveY(currY);

                    boolean alreadyVisited = visitedCoordinates.contains(new Coordinate(currX, currY, 1));
                    if (!alreadyVisited) {
                        visitedCoordinates.add(new Coordinate(currX, currY, 1));
                        // System.out.println(String.format("Add coordinate at: %d, %d", currX, currY));
                    }

                }

                if (visitedCoordinates.size() == oldNumVisitedCoordinates) {
                    numTimesNoNewVisits++;
                    // System.out.println("No new visits");
                } else {
                    numTimesNoNewVisits = 0;
                }

            }

            // System.out.println(
            // String.format("Done checking with new obstacle at %d, %d", coordinate.x(),
            // coordinate.y()));

            if (nextCoordInBounds) {
                // System.out.println(
                // String.format("Found new grid with obstacle at %d, %d", coordinate.x(),
                // coordinate.y()));
                numberOfPossibleNewGrids++;
            }
        }

        System.out.println(String.format("Day 6 part 2: %d", numberOfPossibleNewGrids));
    }

}

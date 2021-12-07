package com.obstrom.day5;

import com.obstrom.day5.grid.Grid;
import com.obstrom.day5.grid.Point;
import com.obstrom.io.InputReader;

import java.util.List;

public class dayFive {

    public void run() {

        System.out.println("\n### DAY 5 ### ");

        List<String> inputList = new InputReader(dayFive.class.getResource("input.txt")).readToList();

        List<Point[]> lineList = inputList.parallelStream()
                .map(line -> {
                    String[] splitLine = line.split(" -> ");
                    String[] firstValue = splitLine[0].split(",");
                    String[] secondValue = splitLine[1].split(",");

                    return new Point[]{
                            new Point(Integer.parseInt(firstValue[0]), Integer.parseInt(firstValue[1])),
                            new Point(Integer.parseInt(secondValue[0]), Integer.parseInt(secondValue[1]))
                    };
                }).toList();

        /*for (Point[] line: lineList) {
            System.out.println("["+line[0]+"], ["+line[1]+"]");
        }*/

        challengeOne(lineList);
        challengeTwo(lineList);

    }

    private String printableLine(Point[] line) {
        return "["+line[0]+"], ["+line[1]+"]";
    }

    private void challengeOne(List<Point[]> lineList) {

        Grid grid = new Grid(1000);

        for (Point[] line: lineList) {

            // If line does not travel straight, skip for this challenge
            if (line[0].getX() != line[1].getX() && line[0].getY() != line[1].getY()) continue;

            grid.markCellsInPath(line[0], line[1]);

        }

        System.out.print("\nChallenge 1 solution: ");
        System.out.println(grid.numOfCountsHigherThenTwo());


    }

    private void challengeTwo(List<Point[]> lineList) {

        Grid grid = new Grid(1000);

        for (Point[] line: lineList) {
            grid.markCellsInPath(line[0], line[1]);
        }

        System.out.print("\nChallenge 2 solution: ");
        System.out.println(grid.numOfCountsHigherThenTwo());

    }

}

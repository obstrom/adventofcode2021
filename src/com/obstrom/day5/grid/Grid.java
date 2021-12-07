package com.obstrom.day5.grid;

import java.util.*;

public class Grid {

    private final List<List<Cell>> cellGrid = new ArrayList<>();

    public class Cell extends Point {

        int count = 0;

        public Cell(int x, int y) {
            super(x, y);
        }

        public void incrementCount() {
            ++this.count;
        }

    }

    public Grid(int size) {
        generateGrid(size);
    }

    public void markCellsInPath(Point point1, Point point2) {
        int deltaX = point2.getX() - point1.getX();
        int deltaY = point2.getY() - point1.getY();

        if (deltaX > 0 && deltaY == 0) {
            // (EAST)
            for (int i = 0; i <= Math.abs(deltaX); i++) {
                cellGrid.get(point1.getY()).get(point1.getX() + i).incrementCount();
            }
        }

        if (deltaX > 0 && deltaY > 0) {
            // (SOUTH EAST)
            for (int i = 0; i <= Math.abs(deltaX); i++) {
                cellGrid.get(point1.getY() + i).get(point1.getX() + i).incrementCount();
            }
        }

        if (deltaX == 0 && deltaY > 0) {
            // (SOUTH)
            for (int i = 0; i <= Math.abs(deltaY); i++) {
                cellGrid.get(point1.getY() + i).get(point1.getX()).incrementCount();
            }
        }

        if (deltaX < 0 && deltaY > 0) {
            // (SOUTH WEST)
            for (int i = 0; i <= Math.abs(deltaX); i++) {
                cellGrid.get(point1.getY() + i).get(point1.getX() - i).incrementCount();
            }
        }

        if (deltaX < 0 && deltaY == 0) {
            // (WEST)
            for (int i = 0; i <= Math.abs(deltaX); i++) {
                cellGrid.get(point2.getY()).get(point2.getX() + i).incrementCount();
            }
        }

        if (deltaX < 0 && deltaY < 0) {
            // (NORTH WEST)
            for (int i = 0; i <= Math.abs(deltaX); i++) {
                cellGrid.get(point1.getY() - i).get(point1.getX() - i).incrementCount();
            }
        }

        if (deltaX == 0 && deltaY < 0) {
            // (NORTH)
            for (int i = 0; i <= Math.abs(deltaY); i++) {
                cellGrid.get(point2.getY() + i).get(point2.getX()).incrementCount();
            }
        }

        if (deltaX > 0 && deltaY < 0) {
            // (NORTH EAST)
            for (int i = 0; i <= Math.abs(deltaX); i++) {
                cellGrid.get(point1.getY() - i).get(point1.getX() + i).incrementCount();
            }
        }
    }

    public Long numOfCountsHigherThenTwo() {
        return cellGrid.stream()
                .flatMap(Collection::stream)
                .filter(cell -> cell.count > 1)
                .count();
    }

    private void generateGrid(int size) {
        for (int y = 0; y < size; y++) {
            List<Cell> row = new ArrayList<>();
            cellGrid.add(row);

            for (int x = 0; x < size; x++) {
                row.add(new Cell(x, y));
            }
        }
    }

}

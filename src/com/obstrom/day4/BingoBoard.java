package com.obstrom.day4;

import java.util.*;
import java.util.stream.Collectors;

public class BingoBoard {

    public class Cell {

        private final int value;
        private boolean marked;

        public Cell(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked(boolean marked) {
            this.marked = marked;
        }

        @Override
        public String toString() {
            return (this.marked) ? "*" + this.value : Integer.toString(this.value);
        }
    }

    private final List<List<Cell>> board = new ArrayList<>();
    private boolean hasWon = false;

    public BingoBoard(List<String> inputBoardString) {
        generateBoard(inputBoardString);
    }

    public void markNumber(int n) {
        board.parallelStream().forEach(row -> row.parallelStream().filter(cell -> cell.getValue() == n).forEach(cell -> cell.setMarked(true)));
    }

    public int calcWinningScore(int playingNumber) {
        return playingNumber * sumUnmarkedScore();
    }

    public boolean checkForBingo() {
        // check for bingo in row
        for (List<Cell> row: board) {
            if (checkLineForAllMatch(row)) return true;
        }

        // check for bingo in column
        for (int column = 0; column < board.get(0).size(); column++) {
            List<Cell> columnList = new ArrayList<>();
            for (int row = 0; row < board.size(); row++) {
                columnList.add(board.get(row).get(column));
            }

            if (checkLineForAllMatch(columnList)) return true;
        }

        return false;
    }

    private int sumUnmarkedScore() {
        return board.parallelStream()
                .flatMap(Collection::stream)
                .filter(cell -> !cell.isMarked())
                .reduce(0, (sum, cell) -> sum + cell.getValue(), Integer::sum);
    }

    private boolean checkLineForAllMatch(List<Cell> line) {
        return line.stream().allMatch(Cell::isMarked);
    }

    private void generateBoard(List<String> input) {
        for (String row : input) {
            this.board.add(
                    Arrays.stream(row.split(" "))
                            .filter(n -> !n.isEmpty())
                            .map(Integer::parseInt)
                            .map(Cell::new)
                            .collect(Collectors.toList())
            );
        }
    }

    public boolean hasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (List<Cell> row: board) {
            output.append(row);
            output.append("\n");
        }
        return output.toString();
    }

}

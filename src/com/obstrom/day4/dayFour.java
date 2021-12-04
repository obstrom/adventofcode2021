package com.obstrom.day4;

import com.obstrom.io.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class dayFour {

    public void run() {

        System.out.println("\n### DAY 4 ### ");

        final List<String> inputList = new InputReader(dayFour.class.getResource("input.txt")).readToList();

        // parse first line of numbers
        final List<Integer> numbers = Arrays.stream(inputList.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        challengeOne(generateBoards(inputList), numbers);
        challengeTwo(generateBoards(inputList), numbers);

    }

    private List<BingoBoard> generateBoards(List<String> inputList) {
        List<BingoBoard> boards =  new ArrayList<>();
        for (int i = 2; i < inputList.size(); i += 6) {
            boards.add(new BingoBoard(inputList.subList(i, i+5)));
        }
        return boards;
    }

    private void challengeOne(List<BingoBoard> boards, List<Integer> numbers) {

        BingoBoard firstWinningBoard = null;
        int winningScore = 0;

        Play:
        for (int number: numbers) {
            for (BingoBoard board: boards) {
                board.markNumber(number);

                if (board.checkForBingo()) {
                    firstWinningBoard = board;
                    winningScore = board.calcWinningScore(number);
                    break Play;
                }
            }
        }

        System.out.println("\nBINGO! - First winner is:\n" + ((firstWinningBoard != null) ? firstWinningBoard : "no board"));
        System.out.print("Challenge 1 solution: ");
        System.out.println(winningScore);

    }

    private void challengeTwo(List<BingoBoard> boards, List<Integer> numbers) {
        BingoBoard lastWinningBoard = null;
        int winningScore = 0;

        for (int number: numbers) {
            for (BingoBoard board: boards) {
                if (board.hasWon()) continue;

                board.markNumber(number);

                if (board.checkForBingo()) {
                    lastWinningBoard = board;
                    board.setHasWon(true);
                    winningScore = board.calcWinningScore(number);
                }
            }
        }

        System.out.println("\nLast winning board is:\n" + ((lastWinningBoard != null) ? lastWinningBoard : "no board"));
        System.out.print("Challenge 2 solution: ");
        System.out.println(winningScore);

    }

}

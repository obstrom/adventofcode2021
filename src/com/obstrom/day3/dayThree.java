package com.obstrom.day3;

import com.obstrom.io.InputReader;

import java.util.List;
import java.util.stream.Collectors;

public class dayThree {

    public void run() {

        System.out.println("\n### DAY 3 ### ");

        List<String> inputList = new InputReader(dayThree.class.getResource("input.txt")).readToList();

        challengeOne(inputList);
        challengeTwo(inputList);

    }

    private void challengeOne(List<String> inputList) {
        StringBuilder gammaRateString = new StringBuilder();
        StringBuilder epsilonRateString = new StringBuilder();

        for (int column = 0; column < inputList.get(0).length(); column++) {
            int numOfOne = 0;
            int numOfZero = 0;

            for (String row : inputList) {
                char c = row.charAt(column);
                if (c == '1') ++numOfOne;
                if (c == '0') ++numOfZero;
            }

            if (numOfOne > numOfZero) {
                gammaRateString.append("1");
                epsilonRateString.append("0");
            } else {
                gammaRateString.append("0");
                epsilonRateString.append("1");
            }
        }

        int gammaRate = Integer.parseInt(String.valueOf(gammaRateString), 2);
        int epsilonRate = Integer.parseInt(String.valueOf(epsilonRateString), 2);

        System.out.print("\nChallenge 1 solution: ");
        System.out.println(gammaRate * epsilonRate);

    }

    private void challengeTwo(List<String> inputList) {

        List<String> oxygenGeneratorRatingList = inputList;
        List<String> CO2ScrubberRatingList = inputList;

        for (int column = 0; column < inputList.get(0).length(); column++) {
            if (oxygenGeneratorRatingList.size() > 0)
                oxygenGeneratorRatingList = filterAtPosition(oxygenGeneratorRatingList, column, true);

            if (CO2ScrubberRatingList.size() > 0)
                CO2ScrubberRatingList = filterAtPosition(CO2ScrubberRatingList, column, false);
        }

        int oxygenGeneratorRating = Integer.parseInt(String.valueOf(oxygenGeneratorRatingList.get(0)), 2);
        int CO2ScrubberRating = Integer.parseInt(String.valueOf(CO2ScrubberRatingList.get(0)), 2);

        System.out.print("\nChallenge 2 solution: ");
        System.out.println(oxygenGeneratorRating * CO2ScrubberRating);

    }

    private List<String> filterAtPosition(List<String> inputList, int index, boolean mostCommon) {

        if (inputList.size() == 1) return inputList;

        int numOfOne = 0;
        int numOfZero = 0;

        for (String row : inputList) {
            char c = row.charAt(index);
            if (c == '1') ++numOfOne;
            if (c == '0') ++numOfZero;
        }

        if (numOfOne == numOfZero && mostCommon) {
            return inputList.stream().filter(row -> row.charAt(index) == '1').collect(Collectors.toList());
        } else if (numOfOne == numOfZero && !mostCommon) {
            return inputList.stream().filter(row -> row.charAt(index) == '0').collect(Collectors.toList());
        } else if (numOfOne > numOfZero && mostCommon || numOfOne < numOfZero && !mostCommon) {
            return inputList.stream().filter(row -> row.charAt(index) == '1').collect(Collectors.toList());
        } else {
            return inputList.stream().filter(row -> row.charAt(index) == '0').collect(Collectors.toList());
        }

    }

}


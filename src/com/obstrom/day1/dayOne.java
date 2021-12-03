package com.obstrom.day1;

import com.obstrom.io.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class dayOne {

    public static void main(String[] args) {

        System.out.println("\n### DAY 1 ### ");

        List<String> inputList = new InputReader(dayOne.class.getResource("input.txt")).readToList();
        List<Integer> numberList = inputList.stream().map(Integer::parseInt).collect(Collectors.toList());

        challengeOne(numberList);
        challengeTwo(numberList);

    }

    private static int findIncreasesInList(List<Integer> numberList) {
        int countIncreases = 0;
        int previousValue = numberList.get(0);

        for (int value: numberList) {
            if (value > previousValue) ++countIncreases;
            previousValue = value;
        }

        return countIncreases;
    }

    private static void challengeOne(List<Integer> numberList) {
        System.out.print("\nChallenge 1 solution: ");
        System.out.println(findIncreasesInList(numberList));

    }

    private static void challengeTwo(List<Integer> numberList) {
        List<Integer> sumList = new ArrayList<>();

        for (int i = 0; i < numberList.size(); i++) {
            try {
                sumList.add(numberList.get(i) + numberList.get(i+1) + numberList.get(i+2));
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }

        System.out.print("\nChallenge 2 solution: ");
        System.out.println(findIncreasesInList(sumList));

    }

}

package com.obstrom.day7;

import com.obstrom.io.InputReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class daySeven {

    public void run() {

        System.out.println("\n### DAY 7 ### ");

        List<String> inputList = new InputReader(daySeven.class.getResource("input.txt")).readToList();

        // parse list to numbers
        ArrayList<Integer> positions = Arrays.stream(inputList.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        challengeOne(positions);
        challengeTwo(positions);

    }

    private int findMedianOfList(ArrayList<Integer> positions) {
        Collections.sort(positions);

        int length = positions.size();
        if (length % 2 == 0) {
            return positions.subList((length/2)-1, (length/2)+1).stream().reduce(0, Integer::sum) / 2;
        } else {
            return positions.get((length/2)-1);
        }
    }

    private void challengeOne(ArrayList<Integer> positions) {

        int median = findMedianOfList(positions);
        int totalFuel = 0;

        for (int position: positions) {
            totalFuel += Math.abs(position - median);
        }

        System.out.print("\nChallenge 1 solution: ");
        System.out.println(totalFuel);

    }

    private int calcDelta(int a, int b) {
        return Math.abs(b - a);
    }

    // nth triangle number formula (https://en.wikipedia.org/wiki/Triangular_number)
    private int triangleNumber(int n) {
        return (n * (n + 1)) / 2;
    }

    private int calcFuelBetweenTwoPoints(int position, int targetPosition) {
        return triangleNumber(calcDelta(targetPosition, position));
    }

    private int sumFuelForAllPositionsForGivenPoint(ArrayList<Integer> positions, int chosenPosition) {
        return positions.stream()
                .reduce(0, (sum, pos) -> sum + calcFuelBetweenTwoPoints(pos, chosenPosition), Integer::sum);
    }

    private void challengeTwo(ArrayList<Integer> positions) {

        // Create a list with values 0 ... 999
        List<Integer> positionsRange = IntStream
                .range(0, (positions.size()-1))
                .boxed()
                .toList();

        // Calculate the total fuel for each possible position
        List<Integer> fuelConsumptionList = positionsRange.parallelStream()
                .map(pos -> sumFuelForAllPositionsForGivenPoint(positions, pos))
                .toList();

        // Find the lowest result
        int lowestFuel = fuelConsumptionList.stream()
                .min(Integer::compare)
                .get();

        System.out.print("\nChallenge 2 solution: ");
        System.out.println(lowestFuel);

    }

}

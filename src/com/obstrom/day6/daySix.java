package com.obstrom.day6;

import com.obstrom.io.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class daySix {

    public void run() {

        System.out.println("\n### DAY 6 ### ");

        List<String> inputList = new InputReader(daySix.class.getResource("input.txt")).readToList();

        Map<Object, Long> fish = Arrays.stream(inputList.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        fish.put(0, 0L);
        fish.put(6, 0L);
        fish.put(7, 0L);
        fish.put(8, 0L);

        challengeOne(fish);
        challengeTwo(fish);

    }

    private void passOneDay(Map<Object, Long> fish) {
        long spawning = fish.get(0);

        // Shift down
        for (int i = 1; i < 9; i++) {
            fish.put(i-1, fish.get(i));
        }

        fish.put(6, fish.get(6) + spawning);
        fish.put(8, spawning);
    }

    private Long getTotal(Map<Object, Long> fish) {
        return fish.values().stream().reduce(0L, Long::sum);
    }

    private void challengeOne(Map<Object, Long> fish) {

        for (int day = 0; day < 80; day++) {
            passOneDay(fish);
        }

        System.out.print("\nChallenge 1 solution: ");
        System.out.println(getTotal(fish));

    }

    private void challengeTwo(Map<Object, Long> fish) {

        for (int day = 0; day < (256-80); day++) {
            passOneDay(fish);
        }

        System.out.print("\nChallenge 2 solution: ");
        System.out.println(getTotal(fish));

    }

}

package com.obstrom.day2;

import com.obstrom.day1.dayOne;
import com.obstrom.io.InputReader;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class dayTwo {

    public class Command {

        private String direction;
        private int value;

        public Command(String direction, int value) {
            this.direction = direction;
            this.value = value;
        }

        public String getDirection() {
            return direction;
        }

        public int getValue() {
            return value;
        }

    }

    public void run() {

        System.out.println("\n### DAY 2 ### ");

        List<String> inputList = new InputReader(dayTwo.class.getResource("input.txt")).readToList();
        List<Command> commandList = inputList.stream().map(line -> {
            String[] splitLine = line.split(" ");
            return new Command(splitLine[0], Integer.parseInt(splitLine[1]));
        }).collect(Collectors.toList());

        challengeOne(commandList);
        //challengeTwo(numberList);

    }

    private void challengeOne(List<Command> commandList) {
        int forwardPos = 0;
        int depth = 0;
        for (Command command: commandList) {
            switch (command.getDirection()) {
                case "forward" -> forwardPos += command.getValue();
                case "down" -> depth += command.getValue();
                case "up" -> depth -= command.getValue();
            }
        }

        System.out.print("\nChallenge 1 solution: ");
        System.out.println(forwardPos * depth);

    }

    private void challengeTwo(Map<String, Integer> commands) {

        System.out.print("\nChallenge 2 solution: ");

    }

}

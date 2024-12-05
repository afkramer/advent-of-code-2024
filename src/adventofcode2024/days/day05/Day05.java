package adventofcode2024.days.day05;

import java.util.ArrayList;
import java.util.List;

import adventofcode2024.Utils;
import adventofcode2024.days.Day;

public class Day05 implements Day {
    private List<List<Integer>> instructions;
    private List<String> pagesToPrintList;

    @Override
    public void part1(String input) {
        init(input);
        // for (List<Integer> instruction : instructions) {
        // Utils.printLists(instruction);
        // }
        int sumMiddlePages = 0;

        for (String listOfPages : pagesToPrintList) {
            String[] pagesRaw = listOfPages.split(",");
            List<Integer> pages = convertStringArrayToIntegerList(pagesRaw);
            boolean validOrder = true;
            for (int i = 0; i < pages.size(); i++) {
                // System.out.println(String.format("Checking page %d", pages.get(i)));
                int currentPage = pages.get(i);

                // Find all instructions with the current page
                List<List<Integer>> relevantInstructions = instructions.stream()
                        .filter(instruction -> instruction.contains(currentPage)).toList();

                for (List<Integer> instruction : relevantInstructions) {
                    // System.out.println("Checking instruction");
                    // Utils.printLists(instruction);
                    int indexOfCurrentPage = instruction.indexOf(currentPage);

                    if (indexOfCurrentPage == 0) {
                        int indexOfOtherPage = pages.indexOf(instruction.get(1));
                        if (indexOfOtherPage != -1 && indexOfOtherPage < i) {
                            validOrder = false;
                            break;
                        }
                    } else {
                        int indexOfOtherPage = pages.indexOf(instruction.get(0));
                        if (indexOfOtherPage != -1 && indexOfOtherPage > i) {
                            validOrder = false;
                            break;
                        }
                    }
                }
            }

            if (validOrder) {
                // System.out.println("Order was correct!");
                int middlePageIndex = pages.size() / 2;
                // System.out.println(String.format("Adding page %d",
                // pages.get(middlePageIndex)));
                sumMiddlePages += pages.get(middlePageIndex);
            }
        }

        System.out.println(String.format("Day 5 part 1: %d", sumMiddlePages));
    }

    @Override
    public void part2(String input) {
        init(input);

        int sumMiddlePages = 0;

        for (String listOfPages : pagesToPrintList) {
            String[] pagesRaw = listOfPages.split(",");
            List<Integer> pages = convertStringArrayToIntegerList(pagesRaw);
            boolean validOrder = true;
            boolean sorted = false;
            while (!sorted) {
                // System.out.println("Checking pages in while loop");
                // Utils.printLists(pages);
                boolean swapped = false;
                for (int i = 0; i < pages.size(); i++) {
                    // System.out.println(String.format("Checking page %d", pages.get(i)));
                    int currentPage = pages.get(i);

                    // Find all instructions with the current page
                    List<List<Integer>> relevantInstructions = instructions.stream()
                            .filter(instruction -> instruction.contains(currentPage)).toList();

                    for (List<Integer> instruction : relevantInstructions) {
                        // System.out.println("Checking instruction");
                        // Utils.printLists(instruction);
                        int indexOfCurrentPageInInstructions = instruction.indexOf(currentPage);
                        int indexOfCurrentPageInPages = pages.indexOf(currentPage);

                        if (indexOfCurrentPageInInstructions == 0) {
                            int indexOfOtherPage = pages.indexOf(instruction.get(1));
                            if (indexOfOtherPage != -1 && indexOfOtherPage < indexOfCurrentPageInPages) {
                                validOrder = false;
                                pages.set(indexOfCurrentPageInPages, instruction.get(1));
                                pages.set(indexOfOtherPage, currentPage);
                                swapped = true;
                                // System.out.println("Swapped");
                                // Utils.printLists(pages);
                            }
                        } else {
                            int indexOfOtherPage = pages.indexOf(instruction.get(0));
                            if (indexOfOtherPage != -1 && indexOfOtherPage > indexOfCurrentPageInPages) {
                                validOrder = false;
                                pages.set(indexOfCurrentPageInPages, instruction.get(0));
                                pages.set(indexOfOtherPage, currentPage);
                                swapped = true;
                                // System.out.println("Swapped");
                                // Utils.printLists(pages);
                            }
                        }
                    }
                    // if (swapped) {
                    // break;
                    // }
                }
                if (!swapped) {
                    sorted = true;
                }
            }

            if (!validOrder) {
                // System.out.println("Order was correct!");
                int middlePageIndex = pages.size() / 2;
                // System.out.println(String.format("Adding page %d",
                // pages.get(middlePageIndex)));
                sumMiddlePages += pages.get(middlePageIndex);
            }
        }

        System.out.println(String.format("Day 5 part 2: %d", sumMiddlePages));
    }

    private List<Integer> convertStringArrayToIntegerList(String[] strings) {
        List<Integer> integers = new ArrayList<>();
        for (String string : strings) {
            integers.add(Integer.parseInt(string));
        }
        return integers;
    }

    private void init(String input) {
        List<String> inputs = Utils.readData(input);
        boolean readingInstructions = true;
        instructions = new ArrayList<>();
        pagesToPrintList = new ArrayList<>();
        for (String line : inputs) {
            if (line.equals("")) {
                readingInstructions = false;
            } else if (readingInstructions) {
                String[] rawInstruction = line.split("\\|");
                List<Integer> instruction = new ArrayList<>();
                instruction.add(Integer.parseInt(rawInstruction[0]));
                instruction.add(Integer.parseInt(rawInstruction[1]));
                instructions.add(instruction);
            } else {
                pagesToPrintList.add(line);
            }
        }
    }

}

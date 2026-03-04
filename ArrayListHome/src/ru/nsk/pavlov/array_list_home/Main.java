package ru.nsk.pavlov.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("lesson7_arrayListHome.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        System.out.println("A list with their file lines: " + fileLines);

        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println();
        System.out.println("There is a list: " + numbers1);

        for (int i = numbers1.size() - 1; i >= 0; i--) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
            }
        }

        System.out.println("A list without even numbers: " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1, 1, 15, 10, 4, 7));
        ArrayList<Integer> nonRepeatingNumbers = new ArrayList<>(numbers2.size());

        System.out.println();
        System.out.println("There is a list: " + numbers2);

        for (Integer integer : numbers2) {
            if (!nonRepeatingNumbers.contains(integer)) {
                nonRepeatingNumbers.add(integer);
            }
        }

        System.out.println("A list without repeated numbers: " + nonRepeatingNumbers);
    }
}

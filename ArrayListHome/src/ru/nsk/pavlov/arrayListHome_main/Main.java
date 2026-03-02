package ru.nsk.pavlov.arrayListHome_main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> numbers1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("lesson7_arrayListHome.txt"));
        ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numbers1.add(line);
            }
        }

        System.out.println("A list with their file lines: " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println();
        System.out.println("There is a list: " + numbers2);

        for (int i = 0; i < numbers2.size(); i++) {
            if (numbers2.get(i) % 2 == 0) {
                numbers2.remove(i);
            }
        }

        System.out.println("A list without even numbers: " + numbers2);

        ArrayList<Integer> numbers3 = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1, 1, 15, 10, 4, 7));
        ArrayList<Integer> numbers4 = new ArrayList<>();

        System.out.println();
        System.out.println("There is a list: " + numbers3);

        for (Integer integer : numbers3) {
            if (!numbers4.contains(integer)) {
                numbers4.add(integer);
            }
        }

        System.out.println("A list without repeated numbers: " + numbers4);
    }
}

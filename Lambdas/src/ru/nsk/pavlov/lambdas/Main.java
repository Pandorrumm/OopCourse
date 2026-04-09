package ru.nsk.pavlov.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Петр", 45),
                new Person("Петр", 19),
                new Person("Александр", 20),
                new Person("Александр", 14),
                new Person("Алиса", 22),
                new Person("Ева", 35),
                new Person("Матвей", 24),
                new Person("Полина", 50),
                new Person("Артем", 10)
        );

        List<String> uniqueNamesList = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        System.out.println("Список уникальных имён:");
        System.out.println(uniqueNamesList);

        String uniqueNamesString = persons.stream()
                .map(Person::name)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println();
        System.out.println(uniqueNamesString);

        OptionalDouble optionalAverage = persons.stream()
                .filter(p -> p.age() < 18)
                .mapToInt(Person::age)
                .average();

        double averageAgeForUnder18Persons;

        if (optionalAverage.isPresent()) {
            averageAgeForUnder18Persons = optionalAverage.getAsDouble();
        } else {
            averageAgeForUnder18Persons = 0.0;
        }

        System.out.println();
        System.out.println("Средний возраст людей младше 18: " + averageAgeForUnder18Persons);

        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingInt(Person::age)
                ));

        System.out.println();
        System.out.println("Map, в котором ключи – имена, а значения – средний возраст:");
        System.out.println(averageAgesByNames);

        List<String> namesFrom20To45 = persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .map(Person::name)
                .toList();

        System.out.println();
        System.out.println("Имена людей от 20 до 45 лет в порядке убывания возраста:");
        System.out.println(namesFrom20To45);
    }
}

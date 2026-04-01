package ru.nsk.pavlov.lambdas_main;

import ru.nsk.pavlov.lambdas_person.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

        String uniqueNames = persons.stream()
                .map(Person::name)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        double averageAge = persons.stream()
                .filter(p -> p.age() < 18)
                .mapToInt(Person::age)
                .average()
                .orElse(0.0);

        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingInt(Person::age)
                ));

        List<String> namesFrom20To45 = persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted(Comparator.comparingInt(Person::age).reversed())
                .map(Person::name)
                .toList();

        System.out.println("Список уникальных имён:");
        System.out.println(uniqueNamesList);

        System.out.println();
        System.out.println(uniqueNames);

        System.out.println();
        System.out.println("Средний возраст людей младше 18: " + averageAge);

        System.out.println();
        System.out.println("Map, в котором ключи – имена, а значения – средний возраст:");
        System.out.println(averageAgeByName);

        System.out.println();
        System.out.println("Имена людей от 20 до 45 лет в порядке убывания возраста:");
        System.out.println(namesFrom20To45);
    }
}

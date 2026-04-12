package ru.nsk.pavlov.csv;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("""
                    Передано не правильное количество аргументов.
                    Необходимо передавать 2 аргумента - путь к исходному CSV файлу и путь к HTML файлу.
                    """);
            return;
        }

        try {
            CsvToHtmlConverter csvToHtmlConverter = new CsvToHtmlConverter();
            csvToHtmlConverter.convert(args[0], args[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка вводв/вывода: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при конвертации: " + e.getMessage());
        }
    }
}

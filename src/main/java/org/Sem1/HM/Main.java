package org.Sem1.HM;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Напишите программу, которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 8, 7, 9, 2, 1, 3, 6, 9, 7, 15, 4);
        AverageOfEvenNumbers(list);
    }

    private static void AverageOfEvenNumbers(List<Integer> list) {
        OptionalDouble average = list.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(Integer::intValue)
                .average();
        if (average.isPresent()) {
            System.out.println("average of even number: " + average.getAsDouble());
        } else {
            System.out.println("no average of even number");
        }
    }

}

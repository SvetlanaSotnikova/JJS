package org.Sem1.Task1;

import java.util.ArrayList;
import java.util.List;

public class Library1 {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>(
                List.of(new Book("Vitea", 50, "kak uznati zizni", 2003),
                        new Book("Katea", 150, "Uchisi", 2021),
                        new Book("Kolea", 200, "Java easy", 2024)
                )
        );

        List<Book> authors = books.stream()
                .filter(book -> book.getAuthor().equals("Katea")).toList();
        System.out.println("Katea:");
        authors.forEach(System.out::println);

        List<Book> years = books.stream()
                .filter(book -> book.getYear() > 2003).toList();
        System.out.println("> 2003:");
        years.forEach(System.out::println);

        List<String> titles = books.stream()
                .map(Book::getTitle).distinct().toList();
        System.out.println("titles: " + titles);
    }
}

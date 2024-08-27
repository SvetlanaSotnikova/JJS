package org.Sem1.Task1;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Vitea", 50, "kak uznati zizni", 2003));
        books.add(new Book("Katea", 150, "Uchisi", 2021));
        books.add(new Book("Kolea", 200, "Java easy", 2024));

        List<Book> authors = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals("Katea")) {
                authors.add(book);
            }
        }
        System.out.println("Katea: " + authors);

        List<Book> years = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() > 2003) {
                years.add(book);
            }
        }
        System.out.println("> 2003: " + years);

        List<Book> titles = new ArrayList<>();
        for (Book book : books) {
            if (!titles.contains(book.getTitle())) {
                titles.add(book);
            }
        }
        System.out.println("titles: " + titles);
    }
}

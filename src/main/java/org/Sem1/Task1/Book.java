package org.Sem1.Task1;

public class Book {

    //region fields
    private final String title;
    private final String author;
    private final int pages;
    private final int year;
    //endregion

    //region Constructors
    public Book(String author, int pages, String title, int year) {
        this.author = author;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }
    //endregion

    //region Methods
    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author: '" + author + '\'' +
                ", title: '" + title + '\'' +
                ", pages: " + pages +
                ", year: " + year +
                '}';
    }
    //endregion
}

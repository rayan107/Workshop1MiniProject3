package com.example.project3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {

    private final IntegerProperty bookId = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty author = new SimpleStringProperty();
    private final StringProperty publisher = new SimpleStringProperty();
    private final IntegerProperty year = new SimpleIntegerProperty();

    public Book(int bookId, String title, String author, String publisher, int year) {
        this.bookId.set(bookId);
        this.title.set(title);
        this.author.set(author);
        this.publisher.set(publisher);
        this.year.set(year);
    }


    public int getBookId() { return bookId.get(); }
    public void setBookId(int id) { bookId.set(id); }
    public IntegerProperty bookIdProperty() { return bookId; }

    public String getTitle() { return title.get(); }
    public void setTitle(String t) { title.set(t); }
    public StringProperty titleProperty() { return title; }

    public String getAuthor() { return author.get(); }
    public void setAuthor(String a) { author.set(a); }
    public StringProperty authorProperty() { return author; }

    public String getPublisher() { return publisher.get(); }
    public void setPublisher(String p) { publisher.set(p); }
    public StringProperty publisherProperty() { return publisher; }

    public int getYear() { return year.get(); }
    public void setYear(int y) { year.set(y); }
    public IntegerProperty yearProperty() { return year; }
}


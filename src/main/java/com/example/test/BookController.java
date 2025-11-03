package com.example.test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.beans.property.*;

public class BookController {




    @FXML private TextField bookIdFld, titleFld, authorFld, publisherFld, yearFld;
    @FXML private Button addBtn, updateBtn, deleteBtn;
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, Number> bookIdCol, yearCol;
    @FXML private TableColumn<Book, String> titleCol, authorCol, publisherCol;
    @FXML private Text errorMsg;


    private final ObservableList<Book> booksList = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        // Setup columns
        bookIdCol.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty());
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        publisherCol.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty());


        booksTable.setItems(booksList);


        booksList.addAll(
                new Book(101, "Think and Grow Rich", "Napoleon Hill", "The Ralston Society", 1937),
                new Book(102, "The 48 Laws of Power", "Robert Greene", "Viking Press", 1998),
                new Book(103, "The Atomic Habits", "James Clear", "Avery", 2018)
        );


        booksTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> showBookDetails(newSel)
        );
    }


    @FXML
    private void onAdd() {
        try {
            int id = Integer.parseInt(bookIdFld.getText());
            String title = titleFld.getText();
            String author = authorFld.getText();
            String publisher = publisherFld.getText();
            int year = Integer.parseInt(yearFld.getText());

            booksList.add(new Book(id, title, author, publisher, year));
            clearFields();
            errorMsg.setText("");
        } catch (NumberFormatException e) {
            errorMsg.setText("Please enter valid numbers for ID and Year.");
        }
    }

    @FXML
    private void onUpdate() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setBookId(Integer.parseInt(bookIdFld.getText()));
                selected.setTitle(titleFld.getText());
                selected.setAuthor(authorFld.getText());
                selected.setPublisher(publisherFld.getText());
                selected.setYear(Integer.parseInt(yearFld.getText()));
                booksTable.refresh();
                errorMsg.setText("");
            } catch (NumberFormatException e) {
                errorMsg.setText("Please enter valid numbers for ID and Year.");
            }
        } else {
            errorMsg.setText("Please select a book to update.");
        }
    }

    @FXML
    private void onDelete() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            booksList.remove(selected);
            clearFields();
            errorMsg.setText("");
        } else {
            errorMsg.setText("Please select a book to delete.");
        }
    }


    private void showBookDetails(Book book) {
        if (book != null) {
            bookIdFld.setText(String.valueOf(book.getBookId()));
            titleFld.setText(book.getTitle());
            authorFld.setText(book.getAuthor());
            publisherFld.setText(book.getPublisher());
            yearFld.setText(String.valueOf(book.getYear()));
        }
    }

    private void clearFields() {
        bookIdFld.clear();
        titleFld.clear();
        authorFld.clear();
        publisherFld.clear();
        yearFld.clear();
    }
}

package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfessorController {

    @FXML
    private TableView<Professor> professorTable;

    @FXML
    private TableColumn<Professor, Integer> idColumn;

    @FXML
    private TableColumn<Professor, String> nameColumn;

    @FXML
    private TableColumn<Professor, String> departmentColumn;

    @FXML
    private TableColumn<Professor, String> emailColumn;

    @FXML
    private TableColumn<Professor, String> phoneColumn;

    @FXML
    private TableColumn<Professor, String> specializationColumn;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField departmentField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField specializationField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button clearButton;

    private ObservableList<Professor> professorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        professorTable.setItems(professorList);

        professorList.add(new Professor(1, "Dr. John Smith", "Computer Science", "j.smith@uni.edu", "123-456-7890", "AI & Machine Learning"));
        professorList.add(new Professor(2, "Dr. Sarah Johnson", "Mathematics", "s.johnson@uni.edu", "123-456-7891", "Applied Mathematics"));

        professorTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    @FXML
    private void handleAdd() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String department = departmentField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String specialization = specializationField.getText();

            if (name.isEmpty() || department.isEmpty() || email.isEmpty() || phone.isEmpty() || specialization.isEmpty()) {
                showAlert("Error", "All fields are required!");
                return;
            }

            Professor newProfessor = new Professor(id, name, department, email, phone, specialization);
            professorList.add(newProfessor);
            clearFields();
            showAlert("Success", "Professor added successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "ID must be a valid number!");
        }
    }

    @FXML
    private void handleUpdate() {
        Professor selectedProfessor = professorTable.getSelectionModel().getSelectedItem();

        if (selectedProfessor == null) {
            showAlert("Error", "Please select a professor to update!");
            return;
        }

        try {
            selectedProfessor.setId(Integer.parseInt(idField.getText()));
            selectedProfessor.setName(nameField.getText());
            selectedProfessor.setDepartment(departmentField.getText());
            selectedProfessor.setEmail(emailField.getText());
            selectedProfessor.setPhone(phoneField.getText());
            selectedProfessor.setSpecialization(specializationField.getText());

            professorTable.refresh();
            clearFields();
            showAlert("Success", "Professor updated successfully!");

        } catch (NumberFormatException e) {
            showAlert("Error", "ID must be a valid number!");
        }
    }

    @FXML
    private void handleDelete() {
        Professor selectedProfessor = professorTable.getSelectionModel().getSelectedItem();

        if (selectedProfessor == null) {
            showAlert("Error", "Please select a professor to delete!");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText("Delete Professor");
        confirmation.setContentText("Are you sure you want to delete " + selectedProfessor.getName() + "?");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                professorList.remove(selectedProfessor);
                clearFields();
                showAlert("Success", "Professor deleted successfully!");
            }
        });
    }

    @FXML
    private void handleClear() {
        clearFields();
    }

    private void populateFields(Professor professor) {
        idField.setText(String.valueOf(professor.getId()));
        nameField.setText(professor.getName());
        departmentField.setText(professor.getDepartment());
        emailField.setText(professor.getEmail());
        phoneField.setText(professor.getPhone());
        specializationField.setText(professor.getSpecialization());
    }
    private void clearFields() {
        idField.clear();
        nameField.clear();
        departmentField.clear();
        emailField.clear();
        phoneField.clear();
        specializationField.clear();
        professorTable.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
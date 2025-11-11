package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        specializationColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        professorTable.setItems(professorList);


        loadProfessorsFromDatabase();

        professorTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void loadProfessorsFromDatabase() {
        professorList.clear();
        String sql = "SELECT * FROM professors";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Professor professor = new Professor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("specialization")
                );
                professorList.add(professor);
            }

        } catch (SQLException e) {
            showAlert("Error", "Failed to load professors from database: " + e.getMessage());
        }
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

            String sql = "INSERT INTO professors (id, name, department, email, phone, specialization) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = DbConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, department);
                pstmt.setString(4, email);
                pstmt.setString(5, phone);
                pstmt.setString(6, specialization);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    Professor newProfessor = new Professor(id, name, department, email, phone, specialization);
                    professorList.add(newProfessor);
                    clearFields();
                    showAlert("Success", "Professor added successfully!");
                }

            } catch (SQLException e) {
                showAlert("Error", "Failed to add professor: " + e.getMessage());
            }

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
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String department = departmentField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String specialization = specializationField.getText();


            String sql = "UPDATE professors SET name = ?, department = ?, email = ?, phone = ?, specialization = ? WHERE id = ?";

            try (Connection conn = DbConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, name);
                pstmt.setString(2, department);
                pstmt.setString(3, email);
                pstmt.setString(4, phone);
                pstmt.setString(5, specialization);
                pstmt.setInt(6, id);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    selectedProfessor.setId(id);
                    selectedProfessor.setName(name);
                    selectedProfessor.setDepartment(department);
                    selectedProfessor.setEmail(email);
                    selectedProfessor.setPhone(phone);
                    selectedProfessor.setSpecialization(specialization);

                    professorTable.refresh();
                    clearFields();
                    showAlert("Success", "Professor updated successfully!");
                }

            } catch (SQLException e) {
                showAlert("Error", "Failed to update professor: " + e.getMessage());
            }

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

                String sql = "DELETE FROM professors WHERE id = ?";

                try (Connection conn = DbConnection.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setInt(1, selectedProfessor.getId());

                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        professorList.remove(selectedProfessor);
                        clearFields();
                        showAlert("Success", "Professor deleted successfully!");
                    }

                } catch (SQLException e) {
                    showAlert("Error", "Failed to delete professor: " + e.getMessage());
                }
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
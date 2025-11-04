package com.example.demo;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CoursesController {

    @FXML
    private TextField crnField;

    @FXML
    private TextField courseNameField;

    @FXML
    private TextField instructorField;

    @FXML
    private TextField creditsField;

    @FXML
    private TextField nbOfStudentsField;

    @FXML
    private TableView<Course> coursesTable;

    @FXML
    private TableColumn<Course, String> crnColumn;

    @FXML
    private TableColumn<Course, String> courseNameColumn;

    @FXML
    private TableColumn<Course, String> instructorColumn;

    @FXML
    private TableColumn<Course, Integer> creditsColumn;

    @FXML
    private TableColumn<Course, Integer> nbOfStudentsColumn;

    @FXML
    private Button createBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button clearBtn;

    private CoursesStore coursesStore;
    private String selectedCrn = null;

    @FXML
    public void initialize() {
        coursesStore = CoursesStore.getInstance();

        // Set up table columns
        crnColumn.setCellValueFactory(new PropertyValueFactory<>("crn"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        instructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
        nbOfStudentsColumn.setCellValueFactory(new PropertyValueFactory<>("nbOfStudents"));

        // Load data into table
        coursesTable.setItems(coursesStore.getCourses());

        // Add selection listener
        coursesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    @FXML
    private void handleCreate() {
        try {
            String crn = crnField.getText().trim();
            String courseName = courseNameField.getText().trim();
            String instructor = instructorField.getText().trim();
            int credits = Integer.parseInt(creditsField.getText().trim());
            int nbOfStudents = Integer.parseInt(nbOfStudentsField.getText().trim());

            if (crn.isEmpty() || courseName.isEmpty() || instructor.isEmpty()) {
                showAlert("Validation Error", "Please fill all fields!");
                return;
            }

            // Check if CRN already exists
            if (coursesStore.getCourse(crn) != null) {
                showAlert("Error", "A course with this CRN already exists!");
                return;
            }

            Course newCourse = new Course(crn, courseName, instructor, credits, nbOfStudents);
            coursesStore.addCourse(newCourse);
            clearFields();
            showAlert("Success", "Course created successfully!");

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Credits and Number of Students must be valid numbers!");
        }
    }

    @FXML
    private void handleUpdate() {
        try {
            if (selectedCrn == null) {
                showAlert("Selection Error", "Please select a course from the table to update!");
                return;
            }

            String crn = crnField.getText().trim();
            String courseName = courseNameField.getText().trim();
            String instructor = instructorField.getText().trim();
            int credits = Integer.parseInt(creditsField.getText().trim());
            int nbOfStudents = Integer.parseInt(nbOfStudentsField.getText().trim());

            if (crn.isEmpty() || courseName.isEmpty() || instructor.isEmpty()) {
                showAlert("Validation Error", "Please fill all fields!");
                return;
            }

            Course updatedCourse = new Course(crn, courseName, instructor, credits, nbOfStudents);

            if (coursesStore.updateCourse(selectedCrn, updatedCourse)) {
                clearFields();
                showAlert("Success", "Course updated successfully!");
                selectedCrn = null;
            } else {
                showAlert("Error", "Failed to update course!");
            }

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Credits and Number of Students must be valid numbers!");
        }
    }

    @FXML
    private void handleDelete() {
        Course selected = coursesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Selection Error", "Please select a course to delete!");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Delete Course");
        confirmAlert.setContentText("Are you sure you want to delete this course?");

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            if (coursesStore.deleteCourse(selected.getCrn())) {
                clearFields();
                showAlert("Success", "Course deleted successfully!");
            } else {
                showAlert("Error", "Failed to delete course!");
            }
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
    }

    private void populateFields(Course course) {
        selectedCrn = course.getCrn();
        crnField.setText(course.getCrn());
        courseNameField.setText(course.getCourseName());
        instructorField.setText(course.getInstructor());
        creditsField.setText(String.valueOf(course.getCredits()));
        nbOfStudentsField.setText(String.valueOf(course.getNbOfStudents()));
    }

    private void clearFields() {
        crnField.clear();
        courseNameField.clear();
        instructorField.clear();
        creditsField.clear();
        nbOfStudentsField.clear();
        selectedCrn = null;
        coursesTable.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }}

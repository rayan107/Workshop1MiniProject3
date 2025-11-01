package desctopapp.workshop1_miniproject3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StudentController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField idField;

    @FXML
    private TextField passwordField;


    @FXML
    private TableView<student> tableView;

    @FXML
    private TableColumn<student, String> nameColumn;

    @FXML
    private TableColumn<student, Integer> ageColumn;

    @FXML
    private TableColumn<student, String> idColumn;

    @FXML
    private TableColumn<student, Integer> passwordColumn;


    @FXML
    private Button insertBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    private final ObservableList<student> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // ربط الأعمدة بالخصائص
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty().asObject());
        // تعبئة الجدول
        tableView.setItems(students);

        // لما نختار صف، تتعبّى الحقول تلقائياً
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                nameField.setText(newSel.getName());
                ageField.setText(String.valueOf(newSel.getAge()));
                idField.setText(newSel.getId());
                passwordField.setText(String.valueOf(newSel.getpassword()));
            }
        });
    }


    @FXML
    private void insertPerson() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String id = idField.getText();
            int password = Integer.parseInt(passwordField.getText());

            if (name.isEmpty() || id.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Please fill in all fields!");
                return;
            }

            students.add(new student(name, age, id, password));
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Student added successfully!");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Age must be a number!");
        }
    }

    // ️ تحديث طالب محدد
    @FXML
    private void updatePerson() {
        student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                selected.setName(nameField.getText());
                selected.setAge(Integer.parseInt(ageField.getText()));
                selected.setId(idField.getText());
                selected.setPassword(Integer.parseInt(passwordField.getText()));

                // تحديث العنصر باللائحة (إجباري لتحديث الجدول)
                int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                tableView.getItems().set(selectedIndex, selected);

                showAlert(Alert.AlertType.INFORMATION, "Student updated successfully!");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Age must be a number!");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Please select a student to update.");
        }
    }

    //  حذف طالب
    @FXML
    private void deletePerson() {
        student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            students.remove(selected);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Student deleted successfully!");
        } else {
            showAlert(Alert.AlertType.WARNING, "Please select a student to delete.");
        }
    }

    //  مسح الحقول
    private void clearFields() {
        nameField.clear();
        ageField.clear();
        idField.clear();
        passwordField.clear();
    }

    //  عرض رسائل تنبي
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

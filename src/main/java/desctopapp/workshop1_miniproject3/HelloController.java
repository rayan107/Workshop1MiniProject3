package desctopapp.workshop1_miniproject3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    public Button Bookbtn;
    // ✅ تعريف الأزرار الموجودة في الـFXML
    @FXML
    private Button studentbtn;

    @FXML
    private Button professorbtn;

    // ============================================================

    @FXML
    private void onStudentClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desctopapp/workshop1_miniproject3/student-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) studentbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Student View");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error loading student-view.fxml: " + e.getMessage());
        }
    }

    // ============================================================

    @FXML
    private void onProfessorClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desctopapp/workshop1_miniproject3/ProfessorView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) professorbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Professor View");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error loading ProfessorView.fxml: " + e.getMessage());
        }
    }

    // ============================================================

    @FXML
    private void onbookclick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desctopapp/workshop1_miniproject3/hello-view1.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) Bookbtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Book View");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("❌ Error loading hello-view1.fxml: " + e.getMessage());
        }
    }
}


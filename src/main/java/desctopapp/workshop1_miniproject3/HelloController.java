package desctopapp.workshop1_miniproject3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

public class HelloController {

    @FXML
    private Button studentbtn;

    @FXML
    private void onStudentClick() {
        try {
            // استدعاء FXML الجديد
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/desctopapp/workshop1_miniproject3/student-view.fxml"));
            Parent root = loader.load();

            // استخدام Stage الحالي
            Scene scene = studentbtn.getScene();
            Stage stage = (Stage) scene.getWindow();

            // تبديل الـ Scene
            stage.setScene(new Scene(root));
            stage.setTitle("Student View");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

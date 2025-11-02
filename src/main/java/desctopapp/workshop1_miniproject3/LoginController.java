package desctopapp.workshop1_miniproject3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField uname;

    @FXML
    private PasswordField pass;

    @FXML
    private Text signinmessage;

    @FXML
    private void handleSignIn() {
        String name = uname.getText().trim();
        String passw = pass.getText().trim();

        // تحقق من بيانات الدخول
        if ((name.equals("admin1") && passw.equals("123")) ||
                (name.equals("admin2") && passw.equals("1234")) ||
                (name.equals("admin3") && passw.equals("12345")) ||
                (name.equals("admin4") && passw.equals("123456"))) {

            try {
                // تحميل الصفحة التالية (Hello View)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/desctopapp/workshop1_miniproject3/hello-view.fxml"));
                Parent root = loader.load();

                // الحصول على الـStage الحالي
                Stage stage = (Stage) uname.getScene().getWindow();

                // التبديل للصفحة الجديدة بنفس النافذة
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("University Home Page");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                signinmessage.setText("⚠ Error loading hello-view.fxml");
            }

        } else {
            signinmessage.setText("❌ Sign In Failed. Try again.");
        }
    }
}

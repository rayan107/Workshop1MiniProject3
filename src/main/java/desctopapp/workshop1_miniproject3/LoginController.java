package desctopapp.workshop1_miniproject3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        String name = uname.getText();
        String passw = pass.getText();

        if ((name.equals("admin1") && passw.equals("123")) ||
                (name.equals("admin2") && passw.equals("1234")) ||
                (name.equals("admin3") && passw.equals("12345")) ||
                (name.equals("admin4") && passw.equals("123456"))) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/desctopapp/workshop1_miniproject3/hello-view.fxml"));
                Scene homePageScene = new Scene(fxmlLoader.load());

                Stage currentStage = (Stage) uname.getScene().getWindow();
                currentStage.close();

                Stage homePageStage = new Stage();
                homePageStage.setTitle("University Home Page");
                homePageStage.setScene(homePageScene);
                homePageStage.setResizable(false);
                homePageStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            signinmessage.setText("Sign In Failed");
        }
    }
}

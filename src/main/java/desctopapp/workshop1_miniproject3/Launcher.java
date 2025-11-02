package desctopapp.workshop1_miniproject3;

import javafx.application.Application;
<<<<<<< HEAD
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launcher class - يشغّل المشروع ويدعم 3 واجهات:
 * ProfessorView.fxml -> hello-view.fxml -> hello-view1.fxml
 */
public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        boolean loaded = false;

        // ✅ المحاولة الأولى: ProfessorView.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfessorView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1000, 800);

            primaryStage.setTitle("Professor Management System");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            loaded = true;
            System.out.println("✅ Loaded ProfessorView.fxml successfully.");

        } catch (Exception e1) {
            System.err.println("⚠️ Error loading ProfessorView.fxml — trying hello-view.fxml...");
            e1.printStackTrace();
        }

        // ✅ المحاولة الثانية: hello-view.fxml
        if (!loaded) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1000, 800);

                primaryStage.setTitle("Hello View");
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
                loaded = true;
                System.out.println("✅ Loaded hello-view.fxml successfully.");

            } catch (Exception e2) {
                System.err.println("⚠️ Error loading hello-view.fxml — trying hello-view1.fxml...");
                e2.printStackTrace();
            }
        }

        // ✅ المحاولة الثالثة: hello-view1.fxml
        if (!loaded) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view1.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 1000, 800);

                primaryStage.setTitle("Hello View 1");
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
                loaded = true;
                System.out.println("✅ Loaded hello-view1.fxml successfully.");

            } catch (Exception e3) {
                System.err.println("❌ Error loading all FXML files.");
                e3.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

=======

public class Launcher {
    public static void main(String[] args) {
        Application.launch(UniversityProject.class, args);
    }
}
>>>>>>> daf288b5939ceb2c666d0e3efea444fa84c3f42e

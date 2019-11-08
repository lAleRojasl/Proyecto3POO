package misingo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Load UI From Game.fxml file
        Pane root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        //Set window size
        root.setPrefSize(1375, 770);

        //Initialize scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

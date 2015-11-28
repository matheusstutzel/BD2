package telas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
//https://www.jetbrains.com/idea/help/developing-a-javafx-hello-world-application-coding-examples.html
//http://docs.oracle.com/javafx/2/get_started/form.htm
public static Stage s;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        //todo trocar titulo e trocar nome dos campos
        primaryStage.setTitle("Banco de Dados II");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
        s = primaryStage;
    }
}

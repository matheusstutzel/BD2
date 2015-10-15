package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    public Label helloWorld;
    public PasswordField PASSWORD;
    public TextField USER;
    public TextField BD;
    public TextField host;

    public void sayHelloWorld(ActionEvent event) throws IOException {
        helloWorld.setText(
                host.getText()+"\n" +
                        BD.getText()+"\n" +
                        USER.getText()+"\n" +
                        PASSWORD.getText()+"\n"
        );

        Parent root = FXMLLoader.load(getClass().getResource("teste.fxml"));
        Main.s.setScene(new Scene(root, 300, 275));
    }
}

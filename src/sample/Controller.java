package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    public Label helloWorld;
    public PasswordField PASSWORD;
    public TextField USER;
    public TextField BD;
    public TextField host;

    public void sayHelloWorld(ActionEvent event) {
        helloWorld.setText(
                host.getText()+"\n" +
                        BD.getText()+"\n" +
                        USER.getText()+"\n" +
                        PASSWORD.getText()+"\n"
        );
    }
}

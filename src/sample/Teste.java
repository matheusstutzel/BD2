package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

/**
 * Created by Matheus on 15/10/2015.
 */
public class Teste {
    public Label txt;

    public void sayHelloWorld(ActionEvent actionEvent) {
        txt.setText("Hello world");
    }
}

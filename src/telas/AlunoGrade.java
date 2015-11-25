package telas;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.awt.*;

/**
 * Created by ameix on 25/11/2015.
 */
public class AlunoGrade {
    public GridPane gridPane;
    @FXML
    private Label customerName;

    void initialize() {
    }

    void initData(String mat) {
        customerName.setText(mat);
    }
}

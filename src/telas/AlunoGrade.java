package telas;

import javafx.scene.layout.GridPane;

/**
 * Created by ameix on 25/11/2015.
 */
public class AlunoGrade {
    public GridPane gridPane;
    private String customerName;

    void initialize() {
    }

    void initData(String mat) {
        customerName = mat;
        System.out.println(customerName);
    }
}

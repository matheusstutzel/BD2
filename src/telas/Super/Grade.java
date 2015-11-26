package telas.Super;

import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Matheus on 26/11/2015.
 */
public abstract class Grade implements Initializable {
    public GridPane gridPane;
    private String matricula;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(String mat) {
        matricula = mat;
        getHorariosByMatricula(matricula);
        //System.out.println(matricula);
    }

    public abstract void getHorariosByMatricula(String mat);
}

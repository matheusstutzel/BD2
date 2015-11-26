package telas;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import telas.Super.NewThing;
import telas.Super.Super;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by ameix on 26/11/2015.
 */
public class AddEditarFilial extends Super implements Initializable {
    public Button btAddEdit;
    public Button btCancel;
    public MenuButton botaoFilial;
    String btText, matricula, cod_filial, newFilial;
    Connection c;

    public void initData(String txt, String cod, String mat) {
        btText = txt;
        cod_filial = cod;
        matricula = mat;
        //System.out.println(matricula);
    }

    public void addEditFilial(ActionEvent actionEvent) {
        setMenuButton(botaoFilial, "Vinculado", "cod_filial,matricula_aluno", new NewThing.ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                newFilial = s.get("cod_filial").toString();
            }
        });
        closeWindow(actionEvent);
    }

    public void closeWindow(ActionEvent actionEvent) {
        closeWindow(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

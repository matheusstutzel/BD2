package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import telas.Super.NewThing;
import telas.Super.Super;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by ameix on 26/11/2015.
 */
public class AddEditarFilial extends Super implements Initializable {
    public Button btAddEdit;
    public Button btCancel;
    public MenuButton botaoFilial;
    String btText, matricula, newFilial;
    Connection c = DBHelper.getInstance().connection;

    public void initData(String txt, String mat) {
        btText = txt;
        matricula = mat;
        //System.out.println(matricula);
    }

    public void addEditFilial(ActionEvent actionEvent) {
        if (btText.equals("Mudar Filial")) {
            try {
                c.createStatement().executeUpdate("UPDATE Vinculado set cod_filial=" + newFilial + ",matricula_aluno=" + matricula);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (btText.equals("Vincular à uma Filial")) {
            try {
                c.createStatement().executeUpdate("INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(" + newFilial + "," + matricula + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        sair(btAddEdit);
    }

    private void sair(Button bt) {
        ((Stage) bt.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuButton(botaoFilial, "Filial", "nome,cod_filial", new NewThing.ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                newFilial = s.get("cod_filial").toString();
            }
        });
    }

    public void closeWindow(ActionEvent actionEvent) {
        sair(btCancel);
    }
}

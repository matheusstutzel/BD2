package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Created by ameix on 06/12/2015.
 */
public class VerProfessor implements Initializable {
    public Label nome;
    String cod_turma;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(String mat) {
        cod_turma = mat;
        Connection c;
        try {
            c = DBHelper.getInstance().connection;
            String SQL = "SELECT nome, Professor.matricula_professor from Ministrar, Professor where Ministrar.matricula_professor=Professor.matricula_professor and cod_turma=" + cod_turma;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                nome.setText(rs.getString("matricula_professor") + " - " + rs.getString("nome"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        //System.out.println(matricula);

    }
}

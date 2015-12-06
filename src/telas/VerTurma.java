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
public class VerTurma implements Initializable {
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
            String SQL = "SELECT Ministrar.cod_turma,Materia.nome from Ministrar,Materia,Turma where Ministrar.matricula_professor=" + cod_turma + " and Ministrar.cod_turma=Turma.cod_turma and Turma.cod_materia=Materia.cod_materia";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            while (rs.next()) {
                nome.setText(rs.getString("cod_turma") + " - " + rs.getString("nome"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        //System.out.println(matricula);

    }
}
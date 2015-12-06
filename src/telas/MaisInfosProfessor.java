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
public class MaisInfosProfessor implements Initializable {
    public Label matricula;
    public Label nome;
    public Label endereco;
    public Label horaAula;
    public Label salario;
    public Label materias;
    String mat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(String mat) {
        this.mat = mat;
        //System.out.println(matricula);
        geraInfos();
    }

    private void geraInfos() {
        Connection c;
        try {
            c = DBHelper.getInstance().connection;
            String SQL = "Select * From Professor Where matricula_professor=" + mat;
            String SQL2 = "Select calculaSalario(" + mat + ") as salario";
            String SQL3 = "Select nome From Materia,Leciona Where Materia.cod_materia=Leciona.cod_materia and matricula_professor=" + mat;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            ResultSet rs2 = c.createStatement().executeQuery(SQL2);
            ResultSet rs3 = c.createStatement().executeQuery(SQL3);
            while (rs.next()) {
                matricula.setText(rs.getString("matricula_professor"));
                nome.setText(rs.getString("nome"));
                endereco.setText(rs.getString("endereco"));
                horaAula.setText(rs.getString("horaAula"));
            }
            while (rs2.next()) {
                salario.setText(rs2.getString("salario"));
            }
            String a = "";
            while (rs3.next()) {
                a = a + rs3.getString("nome") + ",";
            }
            materias.setText(a);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}

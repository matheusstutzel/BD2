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
public class MaisInfosAluno implements Initializable {
    public Label matricula;
    public Label nome;
    public Label endereco;
    public Label turno;
    public Label desconto;
    public Label menBas;
    public Label menTotal;
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
            String SQL = "Select * From Aluno Where matricula_aluno=" + mat;
            String SQL2 = "Select materiasAluno(" + mat + ") as materias";
            String SQL3 = "Select calculaMensalidadeComJuros(" + mat + ") as mensalidade";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            ResultSet rs2 = c.createStatement().executeQuery(SQL2);
            ResultSet rs3 = c.createStatement().executeQuery(SQL3);
            while (rs.next()) {
                matricula.setText(rs.getString("matricula_aluno"));
                nome.setText(rs.getString("nome"));
                endereco.setText(rs.getString("endereco"));
                turno.setText(rs.getString("endereco"));
                desconto.setText(rs.getString("desconto"));
                menBas.setText(rs.getString("mensalidadeBasica"));
            }
            while (rs2.next()) {
                materias.setText(rs2.getString("materias"));
            }
            while (rs3.next()) {
                menTotal.setText(rs3.getString("mensalidade"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}

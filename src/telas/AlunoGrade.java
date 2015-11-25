package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ameix on 25/11/2015.
 */
public class AlunoGrade {
    public GridPane gridPane;
    public HashMap<String, ArrayList<Pair<String, String>>> turmaHoraDia = new HashMap<String, ArrayList<Pair<String, String>>>();
    public HashMap<String, String> turmaDescri = new HashMap<String, String>();
    private String matricula;

    void initialize() {
    }

    void initData(String mat) {
        matricula = mat;
        getHorariosByMatricula(matricula);
        //System.out.println(matricula);
    }

    void getHorariosByMatricula(String mat) {
        Connection c = DBHelper.getInstance().connection;
        try {
            ResultSet rs = c.createStatement().executeQuery("SELECT cod_turma FROM Inscrito WHERE matricula_aluno=" + mat);
            ResultSet rs2;
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    rs2 = c.createStatement().executeQuery("SELECT dia,inicio FROM Marcado WHERE cod_turma=" + rs.getString(i));
                    while (rs2.next()) {
                        for (int j = 1; j <= rs2.getMetaData().getColumnCount(); j = j + 2) {
                            if (turmaHoraDia.get(rs.getString(i)) == null)
                                turmaHoraDia.put(rs.getString(i), new ArrayList<Pair<String, String>>());
                            turmaHoraDia.get(rs.getString(i)).add(new Pair<String, String>(rs2.getString(j), String.valueOf(Integer.parseInt(rs2.getString(j + 1)) - 7)));
                            System.out.println((rs2.getString(j) + ";" + rs2.getString(j + 1)));
                        }
                    }
                }
            }
            for (String key : turmaHoraDia.keySet()) {
                ResultSet rr = c.createStatement().executeQuery("SELECT nome FROM Turma, Materia WHERE Turma.cod_materia=Materia.cod_materia and Turma.cod_turma=" + key);
                while (rr.next()) {
                    for (int i = 1; i <= rr.getMetaData().getColumnCount(); i++) {
                        turmaDescri.put(key, rr.getString(i));
                    }
                }
            }
            for (String key : turmaHoraDia.keySet()) {
                for (int i = 0; i < turmaHoraDia.get(key).size(); i++) {
                    gridPane.add(new Label(key + "\n" + turmaDescri.get(key)), Integer.parseInt(turmaHoraDia.get(key).get(i).getKey()), Integer.parseInt(turmaHoraDia.get(key).get(i).getValue()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

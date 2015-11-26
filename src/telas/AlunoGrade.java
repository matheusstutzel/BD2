package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.scene.control.Label;
import telas.Super.Grade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ameix on 25/11/2015.
 */
public class AlunoGrade extends Grade {
    public HashMap<String, ArrayList<String[]>> turmaHoraDia = new HashMap<String, ArrayList<String[]>>();

    @Override
    public void getHorariosByMatricula(String mat) {
        try {
            ResultSet rs = DBHelper.getInstance().connection.createStatement().executeQuery("select ma.cod_turma,ma.dia,(ma.inicio-7)as inicio,mat.nome from Inscrito i Join Turma t on(i.cod_turma=t.cod_turma) join Marcado ma on (t.cod_turma=ma.cod_turma) join Materia mat on(t.cod_materia=mat.cod_materia) where i.matricula_aluno =" + mat);
            String cod_turma, nome, dia, inicio;
            while (rs.next()) {
                nome = rs.getString("nome");
                dia = rs.getString("dia");
                inicio = rs.getString("inicio");
                cod_turma = rs.getString("cod_turma");
                if (turmaHoraDia.get(cod_turma) == null)
                    turmaHoraDia.put(cod_turma, new ArrayList<String[]>());
                turmaHoraDia.get(cod_turma).add(new String[]{dia, inicio, nome});
                System.out.println((dia + ";" + inicio + ";" + nome));
            }
            for (String key : turmaHoraDia.keySet()) {
                for (String[] v : turmaHoraDia.get(key)) {
                    gridPane.add(new Label(key + "\n" + v[2]), Integer.parseInt(v[0]), Integer.parseInt(v[1]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

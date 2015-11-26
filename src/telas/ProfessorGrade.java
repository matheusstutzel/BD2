package telas;

/**
 * Created by ameix on 26/11/2015.
 */

import br.uerj.bd2_2015_2.DBHelper;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import telas.Super.Grade;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfessorGrade extends Grade {

    public void getHorariosByMatricula(final String mat) {
        new Thread(new Task<HashMap<String, ArrayList<String[]>>>() {
            @Override
            protected HashMap<String, ArrayList<String[]>> call() throws Exception {
                HashMap<String, ArrayList<String[]>> turmaHoraDia = new HashMap<String, ArrayList<String[]>>();
                ResultSet rs = DBHelper.getInstance().connection.createStatement().executeQuery("select ma.cod_turma,ma.dia,(ma.inicio-7)as inicio,mat.nome from Ministrar m Join Turma t on(m.cod_turma=t.cod_turma) join Marcado ma on (t.cod_turma=ma.cod_turma) join Materia mat on(t.cod_materia=mat.cod_materia) where m.matricula_professor = " + mat);
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
                return turmaHoraDia;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                HashMap<String, ArrayList<String[]>> turmaHoraDia = null;
                try {
                    turmaHoraDia = get();
                    for (String key : turmaHoraDia.keySet()) {
                        for (String[] v : turmaHoraDia.get(key)) {
                            gridPane.add(new Label(key + "\n" + v[2]), Integer.parseInt(v[0]), Integer.parseInt(v[1]));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


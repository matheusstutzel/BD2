package telas;

import javafx.scene.control.MenuButton;
import telas.Super.NewThing;

import java.util.HashMap;

/**
 * Created by Matheus on 26/11/2015.
 */
public class Inscrito extends NewThing {
    public MenuButton botaoTurma;
    private String turma;
    private String mat;

    @Override
    protected void testaInformacoes() {
        if (turma == null || turma.trim().toLowerCase().equals("Escolher Turma".trim().toLowerCase()) || !testaString(turma))
            throw new IllegalArgumentException("Escolha uma Turma");
    }

    @Override
    protected HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("matricula_aluno", mat);
        hm.put("cod_turma", turma);
        return hm;
    }

    public void init(String mat) {
        this.mat = mat;
        String sql = "select m.nome as nome,t.cod_turma from Turma t join Materia m on(t.cod_materia=m.cod_materia) where t.cod_turma not in(select i.cod_turma from Inscrito i where i.matricula_aluno=" + mat + ")";
        setMenuButton(botaoTurma, sql, "nome,cod_turma", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                turma = s.get("cod_turma").toString();
            }
        }, false);
    }
}

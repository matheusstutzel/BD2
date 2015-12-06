package telas;

import javafx.scene.control.MenuButton;
import telas.Super.NewThing;

import java.util.HashMap;

/**
 * Created by Matheus on 26/11/2015.
 */
public class Marcado extends NewThing {
    public MenuButton botaoDiaInicio;
    private String dia;
    private String inicio;
    private String mat;

    @Override
    protected void testaInformacoes() {
        if (inicio == null || inicio.trim().toLowerCase().equals("Escolher Turma".trim().toLowerCase()) || !testaString(inicio))
            throw new IllegalArgumentException("Escolha uma Turma");
    }

    @Override
    protected HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("cod_turma", mat);
        hm.put("dia", dia);
        hm.put("inicio", inicio);
        return hm;
    }

    public void init(String mat) {
        this.mat = mat;
        String sql = "Select dia,inicio From Horario Where (Select dia,inicio From Marcado Where cod_turma=" + mat + ") not in (Select * From Horario)";
        //String sql = "select m.nome as nome,t.cod_turma from Turma t join Materia m on(t.cod_materia=m.cod_materia) where t.cod_turma not in(select i.cod_turma from Inscrito i where i.matricula_aluno=" + mat + ")";
        setMenuButton(botaoDiaInicio, sql, "dia,inicio", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                dia = s.get("dia").toString();
                inicio = s.get("inicio").toString();
            }
        }, false);
    }
}

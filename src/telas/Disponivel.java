package telas;

import javafx.scene.control.MenuButton;
import telas.Super.NewThing;

import java.util.HashMap;

/**
 * Created by Matheus on 26/11/2015.
 */
public class Disponivel extends NewThing {
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
        hm.put("matricula_professor", mat);
        hm.put("dia", dia);
        hm.put("inicio", inicio);
        return hm;
    }

    public void init(String mat) {
        this.mat = mat;
        String sql = "Select CAST(concat(dia,'-',inicio) AS CHAR(10000) CHARACTER SET utf8) as a,dia,inicio From Horario Where (dia,inicio) not in (Select dia,inicio From Disponivel where matricula_professor=" + mat + ")";
        setMenuButton(botaoDiaInicio, sql, "a,dia,inicio", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                dia = s.get("dia").toString();
                inicio = s.get("inicio").toString();
            }
        }, false);
    }
}

package telas;

import javafx.scene.control.MenuButton;
import telas.Super.NewThing;

import java.util.HashMap;

/**
 * Created by Matheus on 26/11/2015.
 */
public class Ministrar extends NewThing {
    public MenuButton botaoDiaInicio;
    private String cod;
    private String mat;

    @Override
    protected void testaInformacoes() {
        if (mat == null || mat.trim().toLowerCase().equals("Escolher Turma".trim().toLowerCase()) || !testaString(mat))
            throw new IllegalArgumentException("Escolha uma Turma");
    }

    @Override
    protected HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("matricula_professor", mat);
        hm.put("cod_turma", cod);
        return hm;
    }

    public void init(String mat) {
        this.mat = mat;
        String sql = "Select cod_turma From Turma Where (cod_turma) not in (Select cod_turma From Ministrar Where matricula_professor=" + mat + ")";
        setMenuButton(botaoDiaInicio, sql, "cod_turma", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                cod = s.get("cod_turma").toString();
            }
        }, false);
    }
}

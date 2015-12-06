package telas;

import javafx.scene.control.MenuButton;
import telas.Super.NewThing;

import java.util.HashMap;

/**
 * Created by Matheus on 26/11/2015.
 */
public class Leciona extends NewThing {
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
        hm.put("cod_materia", cod);
        return hm;
    }

    public void init(String mat) {
        this.mat = mat;
        String sql = "Select nome,cod_materia From Materia Where (cod_materia) not in (Select cod_materia From Leciona Where matricula_professor=" + mat + ")";
        setMenuButton(botaoDiaInicio, sql, "nome,cod_materia", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                cod = s.get("cod_materia").toString();
            }
        }, false);
    }
}

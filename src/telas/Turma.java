package telas;

import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import telas.Super.NewThing;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Turma extends NewThing {
    public TextField CodigoTexto;
    public MenuButton botaoSala;
    public MenuButton botaoMateria;
    public TextField numAlunosTexto;
    public TextField AnoTexto;
    String codigo, sala, materia, alunos, ano;
    private String cod_filial;

    @Override
    protected void testaInformacoes() {
        codigo = CodigoTexto.getText();
        sala = botaoSala.getText();
        alunos = numAlunosTexto.getText();
        ano = AnoTexto.getText();

        if (!testaLong(codigo, 0L, Long.MAX_VALUE)) {
            throw new IllegalArgumentException("O código deve ser um número positivo");
        } else if (sala.trim().toLowerCase().equals("Escolher Sala".trim().toLowerCase()) || !testaString(sala)) {
            throw new IllegalArgumentException("Escolha uma Sala");
        } else if (materia == null || materia.trim().toLowerCase().equals("Escolher Matéria".trim().toLowerCase()) || !testaString(materia)) {
            throw new IllegalArgumentException("Escolha uma Matéria");
        } else if (!testaInt(alunos, 10, 50)) {
            throw new IllegalArgumentException("O número de alunos deve ser preenchido com um inteiro entre 10 e 50");
        } else if (!testaInt(ano, 0, Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("O ano deve ser um número positivo");
        }
    }

    @Override
    protected HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("cod_turma", codigo);
        hm.put("num_sala", sala);
        hm.put("cod_filial", cod_filial);
        hm.put("cod_materia", materia);
        hm.put("numMaxAlunos", alunos);
        hm.put("ano", ano);
        return hm;
    }
    /* Clicando no botão da Sala, abre uma pop-up com as informações das Salas cadastradas e o usuário pode escolher
        uma. Clicando no botão de Matéria, a mesma coisa só que com Matéria. */
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando quais os horários da turma (melhor fazer outra tela??) */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuButton(botaoSala, "Sala", "num_sala,cod_filial", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                cod_filial = s.get("cod_filial").toString();
            }
        });
        setMenuButton(botaoMateria, "Materia", "nome,cod_materia", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                materia = s.get("cod_materia").toString();
            }
        });
    }
}

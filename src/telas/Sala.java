package telas;

import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Sala extends NewThing {
    public TextField CodigoTexto;

    public TextField numAlunosTexto;
    public MenuButton botaoFilial;


    String codigo, filial, alunos;


    @Override
    void testaInformacoes() {
        codigo = CodigoTexto.getText();
        alunos = numAlunosTexto.getText();
        //System.out.println(codigo);System.out.println(filial);System.out.println(alunos);
        if (!testaLong(codigo, 0l, Long.MAX_VALUE)) {
            throw new IllegalArgumentException("O código deve ser um número positivo");
        } else if (filial == null || filial.trim().toLowerCase().equals("Escolher Filial".trim().toLowerCase()) || !testaString(filial)) {
            throw new IllegalArgumentException("Escolha uma filial");
        } else if (!testaInt(alunos, 10, 50)) {
            throw new IllegalArgumentException("O número de alunos deve ser preenchido com um inteiro entre 10 e 50");
        }
    }

    @Override
    HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("num_sala", codigo);
        hm.put("cod_filial", filial);
        hm.put("numMaxAlunos", alunos);
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuButton(botaoFilial, "Filial", "nome, cod_filial", new ItemClickListener() {
            @Override
            public void onAction(HashMap<String, Object> s) {
                filial = s.get("cod_filial").toString();
            }
        });
    }
     /* Clicando no botão da Filial, abre uma pop-up com as informações das Filiais cadastradas e o usuário pode escolher
        uma. */
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

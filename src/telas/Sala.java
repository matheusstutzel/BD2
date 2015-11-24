package telas;

import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Sala extends NewThing {
    public TextField CodigoTexto;

    public TextField numAlunosTexto;
    public MenuButton botaoFilial;

    @Override
    boolean insere() {
        String codigo, filial, alunos;
        codigo = CodigoTexto.getText();
        filial = botaoFilial.getText();
        alunos = numAlunosTexto.getText();
        //System.out.println(codigo);System.out.println(filial);System.out.println(alunos);
        if (!testaLong(codigo, 0l, Long.MAX_VALUE)) {
            new Alert(Alert.AlertType.INFORMATION, "O código deve ser um número positivo").show();
            return false;
        } else if (filial.trim().toLowerCase().equals("Escolher Filial".trim().toLowerCase()) || !testaString(filial)) {
            new Alert(Alert.AlertType.INFORMATION, "Escolha uma filial").show();
            return false;
        } else if (!testaInt(alunos, 10, 50)) {
            new Alert(Alert.AlertType.INFORMATION, "O número de alunos deve ser preenchido com um inteiro entre 10 e 50").show();
            return false;
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuButton(botaoFilial, "Filial", "nome");
    }
     /* Clicando no botão da Filial, abre uma pop-up com as informações das Filiais cadastradas e o usuário pode escolher
        uma. */
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

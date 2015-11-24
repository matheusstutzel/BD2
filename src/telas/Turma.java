package telas;

import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
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

    @Override
    boolean insere() {
        String codigo, sala, materia, alunos, ano;
        codigo = CodigoTexto.getText();
        sala = botaoSala.getText();
        materia = botaoMateria.getText();
        alunos = numAlunosTexto.getText();
        ano = AnoTexto.getText();

        if (!testaLong(codigo, 0L, Long.MAX_VALUE)) {
            new Alert(Alert.AlertType.INFORMATION, "O código deve ser um número positivo").show();
            return false;
        } else if (sala.trim().toLowerCase().equals("Escolher Sala".trim().toLowerCase()) || !testaString(sala)) {
            new Alert(Alert.AlertType.INFORMATION, "Escolha uma Sala").show();
            return false;
        } else if (materia.trim().toLowerCase().equals("Escolher Matéria".trim().toLowerCase()) || !testaString(materia)) {
            new Alert(Alert.AlertType.INFORMATION, "Escolha uma Matéria").show();
            return false;
        } else if (!testaInt(alunos, 10, 50)) {
            new Alert(Alert.AlertType.INFORMATION, "O número de alunos deve ser preenchido com um inteiro entre 10 e 50").show();
            return false;
        } else if (!testaInt(ano, 0, Integer.MAX_VALUE)) {
            new Alert(Alert.AlertType.INFORMATION, "O ano deve ser um número positivo").show();
            return false;
        }
        return false;
    }
    /* Clicando no botão da Sala, abre uma pop-up com as informações das Salas cadastradas e o usuário pode escolher
        uma. Clicando no botão de Matéria, a mesma coisa só que com Matéria. */
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando quais os horários da turma (melhor fazer outra tela??) */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuButton(botaoSala, "Sala", "num_sala");
        setMenuButton(botaoMateria, "Materia", "nome");
    }
}

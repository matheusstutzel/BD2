package telas;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Aluno extends NewThing {
    public TextField MatriculaTexto;
    public TextField NomeTexto;
    public TextField EnderecoTexto;
    public MenuButton turnoCombo;
    public TextField descontoTexto;
    public TextField mensalidadeTexto;

    @Override
    boolean insere() {
        return false;
    }

    public void atualizaTurnoM(ActionEvent actionEvent) {
        turnoCombo.setText("Manhã");
    }

    public void atualizaTurnoT(ActionEvent actionEvent) {
        turnoCombo.setText("Tarde");
    }

    public void atualizaTurnoN(ActionEvent actionEvent) {
        turnoCombo.setText("Noite");
    }

    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando a quais filiais o aluno tá vinculado
        e quais turmas ele tá inscrito (melhor fazer outra tela??) */
}

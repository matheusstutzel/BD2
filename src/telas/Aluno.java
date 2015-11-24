package telas;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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
        if (!testaString(MatriculaTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite a matrícula").show();
            return false;
        } else if (!testaLong(MatriculaTexto.getText(), 1000000000, 9999999999L)) {
            new Alert(Alert.AlertType.INFORMATION, "A matricula deve ter exatamente 10 digitos").show();
            return false;
        } else if (!testaString(NomeTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o nome do aluno").show();
            return false;
        } else if (!testaString(EnderecoTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o endereço").show();
            return false;
        } else if (!testaString(turnoCombo.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Selecione um turno").show();
            return false;
        } else if (!testaFloat(descontoTexto.getText(), 0, 150)) {
            new Alert(Alert.AlertType.INFORMATION, "Desconto inválido").show();
            return false;
        } else if (!testaString(descontoTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o desconto").show();
            return false;
        } else if (!testaString(mensalidadeTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite a mensalidade").show();
            return false;
        } else if (!testaFloat(mensalidadeTexto.getText(), 0, 1000)) {
            new Alert(Alert.AlertType.INFORMATION, "Mensalidade inválida").show();
            return false;
        }
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

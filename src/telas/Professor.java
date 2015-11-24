package telas;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Professor extends NewThing {
    public TextField MatriculaTexto;
    public TextField NomeTexto;
    public TextField EnderecoTexto;
    public TextField horaAulaTexto;

    @Override
    boolean insere() {
        if (!testaString(MatriculaTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite a código da matéria").show();
            return false;
        } else if (!testaLong(MatriculaTexto.getText(), 1000000000, 9999999999L)) {
            new Alert(Alert.AlertType.INFORMATION, "O código da matéria deve ter exatamente 10 digitos").show();
            return false;
        } else if (!testaString(NomeTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o nome da matéria").show();
            return false;
        } else if (!testaString(EnderecoTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o nome da matéria").show();
            return false;
        } else if (!testaString(horaAulaTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o nome da matéria").show();
            return false;
        } else if (!testaFloat(horaAulaTexto.getText(), 0, 1000)) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o nome da matéria").show();
            return false;
        }
        return false;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando quais matérias o professor leciona, quais horários o professor tem disponível
        e quais turmas ele ministra (melhor fazer outra tela??) */
}

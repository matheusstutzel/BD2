package telas;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Materia extends NewThing {
    public TextField CodigoTexto;
    public TextField NomeTexto;
    public TextField horasPorSemanaTexto;
    public TextField MensalidadeTexto;

    @Override
    boolean insere() {
        if (!testaString(CodigoTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite a código da matéria").show();
            return false;
        } else if (!testaLong(CodigoTexto.getText(), 1000000000, 9999999999L)) {
            new Alert(Alert.AlertType.INFORMATION, "O código da matéria deve ter exatamente 10 digitos").show();
            return false;
        } else if (!testaString(NomeTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o nome da matéria").show();
            return false;
        } else if (!testaInt(horasPorSemanaTexto.getText(), 1, 6)) {
            new Alert(Alert.AlertType.INFORMATION, "Horário inválido").show();
            return false;
        } else if (!testaString(horasPorSemanaTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o horário").show();
            return false;
        } else if (!testaString(MensalidadeTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite a mensalidade").show();
            return false;
        } else if (!testaFloat(MensalidadeTexto.getText(), 0, 1000)) {
            new Alert(Alert.AlertType.INFORMATION, "Mensalidade inválida").show();
            return false;
        }
        return false;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

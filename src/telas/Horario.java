package telas;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Horario extends NewThing {
    public TextField diaTexto;
    public TextField inicioTexto;

    @Override
    boolean insere() {
        if (!testaString(diaTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o dia").show();
            return false;
        } else if (!testaInt(diaTexto.getText(), 1, 6)) {
            new Alert(Alert.AlertType.INFORMATION, "O dia deve estar entre 1 e 6").show();
            return false;
        } else if (!testaString(inicioTexto.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Digite o início").show();
            return false;
        } else if (!testaInt(inicioTexto.getText(), 8, 21)) {
            new Alert(Alert.AlertType.INFORMATION, "O início deve ser entre 8h e 21h").show();
            return false;
        } else if (Integer.parseInt(inicioTexto.getText()) == 12 || Integer.parseInt(inicioTexto.getText()) == 21) {
            new Alert(Alert.AlertType.INFORMATION, "Horário reservado ao intervalo entre turnos").show();
            return false;
        }
        return false;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

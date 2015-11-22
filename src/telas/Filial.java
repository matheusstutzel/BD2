package telas;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Filial extends NewThing {
    public TextField CodigoTexto;
    public TextField NomeTexto;
    public TextField EnderecoTexto;

    @Override
    boolean insere() {
        String codigo, nome, end;
        codigo = CodigoTexto.getText();
        nome = NomeTexto.getText();
        end = EnderecoTexto.getText();
        if (!testaLong(codigo, 0l, Long.MAX_VALUE)) {
            new Alert(Alert.AlertType.INFORMATION, "O código deve ser um número positivo").show();
            return false;
        } else if (!testaString(nome)) {
            new Alert(Alert.AlertType.INFORMATION, "O nome não pode estar vazio").show();
            return false;
        } else if (!testaString(end)) {
            new Alert(Alert.AlertType.INFORMATION, "O endereço não pode estar vazio").show();
            return false;
        }
        return false;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

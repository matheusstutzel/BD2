package telas;

import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Horario extends NewThing {
    public TextField diaTexto;
    public TextField inicioTexto;

    @Override
    boolean insere() {
        return false;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

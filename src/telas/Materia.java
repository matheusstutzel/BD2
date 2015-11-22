package telas;

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
        return false;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

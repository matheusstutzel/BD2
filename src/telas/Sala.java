package telas;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Sala extends NewThing {
    public TextField CodigoTexto;
    public Button botaoFilial;
    public TextField numAlunosTexto;

    @Override
    boolean insere() {
        return false;
    }
     /* Clicando no botão da Filial, abre uma pop-up com as informações das Filiais cadastradas e o usuário pode escolher
        uma. */
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

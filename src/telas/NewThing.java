package telas;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * Created by Matheus on 22/11/2015.
 */
public abstract class NewThing {
    public Button botaoSalvar;
    public Button botaoVoltar;

    public void salva(ActionEvent actionEvent) {
        if (insere()) sair();
    }

    abstract boolean insere();

    public void volta(ActionEvent actionEvent) {
        sair();
    }

    private void sair() {
    }

}

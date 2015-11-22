package telas;

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
        if (!testaString(MatriculaTexto.getText())) return false;
        if (!testaLong(MatriculaTexto.getText(), 1000000000, 9999999999l)) return false;
        if (!testaString(NomeTexto.getText())) return false;
        if (!testaString(EnderecoTexto.getText())) return false;
        if (!testaString(turnoCombo.getText())) return false;
        if (!testaFloat(descontoTexto.getText(), 0, 150)) return false;
        if (!testaString(descontoTexto.getText())) return false;
        if (!testaString(mensalidadeTexto.getText())) return false;
        if (!testaFloat(mensalidadeTexto.getText(), 0, 1000)) return false;
        return false;
    }

    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando a quais filiais o aluno tá vinculado
        e quais turmas ele tá inscrito (melhor fazer outra tela??) */
}

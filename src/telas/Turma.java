package telas;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Turma extends NewThing {
    public TextField CodigoTexto;
    public Button botaoSala;
    public Button botaoMateria;
    public TextField numAlunosTexto;
    public TextField AnoTexto;

    @Override
    boolean insere() {
        return false;
    }
    /* Clicando no botão da Sala, abre uma pop-up com as informações das Salas cadastradas e o usuário pode escolher
        uma. Clicando no botão de Matéria, a mesma coisa só que com Matéria. */
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando quais os horários da turma (melhor fazer outra tela??) */
}

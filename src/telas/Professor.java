package telas;

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
        return false;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando quais matérias o professor leciona, quais horários o professor tem disponível
        e quais turmas ele ministra (melhor fazer outra tela??) */
}

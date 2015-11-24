package telas;

import javafx.scene.control.TextField;

import java.util.HashMap;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Professor extends NewThing {
    public TextField MatriculaTexto;
    public TextField NomeTexto;
    public TextField EnderecoTexto;
    public TextField horaAulaTexto;

    String matricula, nome, endereco, hora;

    @Override
    void testaInformacoes() {
        matricula = MatriculaTexto.getText();
        nome = NomeTexto.getText();
        endereco = EnderecoTexto.getText();
        hora = horaAulaTexto.getText();
        if (!testaString(matricula)) {
            throw new IllegalArgumentException("Digite a código da matéria");
        } else if (!testaLong(matricula, 1000000000, 9999999999L)) {
            throw new IllegalArgumentException("O código da matéria deve ter exatamente 10 digitos");
        } else if (!testaString(nome)) {
            throw new IllegalArgumentException("Digite o nome da matéria");
        } else if (!testaString(endereco)) {
            throw new IllegalArgumentException("Digite o endereço do professor");
        } else if (!testaFloat(hora, 0, 1000)) {
            throw new IllegalArgumentException("Digite o valor da hora aula");
        }
    }

    @Override
    HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("matricula_professor", matricula);
        hm.put("horaAula", hora);
        hm.put("endereco", endereco);
        hm.put("nome", nome);
        return null;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando quais matérias o professor leciona, quais horários o professor tem disponível
        e quais turmas ele ministra (melhor fazer outra tela??) */
}

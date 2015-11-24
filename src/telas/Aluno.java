package telas;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.HashMap;

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
    String matricula, nome, endereco, turno, desconto, mensalidade;

    @Override
    void testaInformacoes() {
        matricula = MatriculaTexto.getText();
        nome = NomeTexto.getText();
        endereco = EnderecoTexto.getText();
        turno = turnoCombo.getText();
        desconto = descontoTexto.getText();
        mensalidade = mensalidadeTexto.getText();
        if (!testaString(matricula)) {
            throw new IllegalArgumentException("Digite a matrícula");
        } else if (!testaLong(matricula, 0, 9999999999L) && matricula.length() == 10) {
            throw new IllegalArgumentException("A matricula deve ter exatamente 10 digitos");
        } else if (!testaString(nome)) {
            throw new IllegalArgumentException("Digite o nome do aluno");
        } else if (!testaString(endereco)) {
            throw new IllegalArgumentException("Digite o endereço");
        } else if (!testaString(turno)) {
            throw new IllegalArgumentException("Selecione um turno");
        } else if (!testaFloat(desconto, 0, 50)) {
            throw new IllegalArgumentException("Desconto inválido");
        } else if (!testaString(desconto)) {
            throw new IllegalArgumentException("Digite o desconto");
        } else if (!testaString(mensalidade)) {
            throw new IllegalArgumentException("Digite a mensalidade");
        } else if (!testaFloat(mensalidade, 0, 1000)) {
            throw new IllegalArgumentException("Mensalidade inválida");
        }
    }

    @Override
    HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("matricula_aluno", matricula);
        hm.put("nome", nome);
        hm.put("desconto", desconto);
        hm.put("endereco", endereco);
        hm.put("turno", turno);
        hm.put("mensalidadeBasica", mensalidade);
        return hm;
    }

    public void atualizaTurno(ActionEvent actionEvent) {
        turnoCombo.setText(((MenuItem) actionEvent.getSource()).getText());
    }


    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
    /* Depois de salvar, abrir uma pop-up perguntando a quais filiais o aluno tá vinculado
        e quais turmas ele tá inscrito (melhor fazer outra tela??) */
}

package telas;

import javafx.scene.control.TextField;
import telas.Super.NewThing;

import java.util.HashMap;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Materia extends NewThing {
    public TextField CodigoTexto;
    public TextField NomeTexto;
    public TextField horasPorSemanaTexto;
    public TextField MensalidadeTexto;
    String codigo, nome, horas, mensalidade;
    @Override
    protected void testaInformacoes() {
        codigo = CodigoTexto.getText();
        nome = NomeTexto.getText();
        horas = horasPorSemanaTexto.getText();
        mensalidade = MensalidadeTexto.getText();
        if (!testaString(codigo)) {
            throw new IllegalArgumentException("Digite a código da matéria");
        } else if (!testaLong(codigo, 1000000000, 9999999999L)) {
            throw new IllegalArgumentException("O código da matéria deve ter exatamente 10 digitos");
        } else if (!testaString(nome)) {
            throw new IllegalArgumentException("Digite o nome da matéria");
        } else if (!testaInt(horas, 1, 6)) {
            throw new IllegalArgumentException("Horário inválido");
        } else if (!testaString(horas)) {
            throw new IllegalArgumentException("Digite o horário");
        } else if (!testaString(mensalidade)) {
            throw new IllegalArgumentException("Digite a mensalidade");
        } else if (!testaFloat(mensalidade, 0, 1000)) {
            throw new IllegalArgumentException("Mensalidade inválida");
        }
    }

    @Override
    protected HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("cod_materia", codigo);
        hm.put("horasPorSemana", horas);
        hm.put("mensalidade", mensalidade);
        hm.put("nome", nome);
        return hm;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

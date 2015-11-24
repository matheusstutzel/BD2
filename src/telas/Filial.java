package telas;

import javafx.scene.control.TextField;

import java.util.HashMap;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Filial extends NewThing {
    public TextField CodigoTexto;
    public TextField NomeTexto;
    public TextField EnderecoTexto;

    String codigo, nome, end;
    @Override
    void testaInformacoes() {
        codigo = CodigoTexto.getText();
        nome = NomeTexto.getText();
        end = EnderecoTexto.getText();
        if (!testaLong(codigo, 0l, Long.MAX_VALUE)) {
            throw new IllegalArgumentException("O código deve ser um número positivo");
        } else if (!testaString(nome)) {
            throw new IllegalArgumentException("O nome não pode estar vazio");
        } else if (!testaString(end)) {
            throw new IllegalArgumentException("O endereço não pode estar vazio");
        }
    }

    @Override
    HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("cod_filial", codigo);
        hm.put("endereco", end);
        hm.put("nome", nome);
        return hm;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

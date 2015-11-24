package telas;

import javafx.scene.control.TextField;

import java.util.HashMap;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Horario extends NewThing {
    public TextField diaTexto;
    public TextField inicioTexto;

    String dia, inicio;
    @Override
    void testaInformacoes() {
        dia = diaTexto.getText();
        inicio = inicioTexto.getText();
        if (!testaString(dia)) {
            throw new IllegalArgumentException("Digite o dia");
        } else if (!testaInt(dia, 1, 6)) {
            throw new IllegalArgumentException("O dia deve estar entre 1 e 6");
        } else if (!testaString(inicio)) {
            throw new IllegalArgumentException("Digite o início");
        } else if (!testaInt(inicio, 8, 21)) {
            throw new IllegalArgumentException("O início deve ser entre 8h e 21h");
        } else if (Integer.parseInt(inicio) == 12 || Integer.parseInt(inicio) == 17) {
            throw new IllegalArgumentException("Horário reservado ao intervalo entre turnos");
        }
    }

    @Override
    HashMap<String, String> getParametros() {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("dia", dia);
        hm.put("inicio", inicio);
        return null;
    }
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

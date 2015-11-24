package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 * Created by Rafaela C. Brum on 17/11/2015.
 */
public class Sala extends NewThing {
    public TextField CodigoTexto;

    public TextField numAlunosTexto;
    public MenuButton botaoFilial;

    @Override
    boolean insere() {
        String codigo, filial, alunos;
        codigo = CodigoTexto.getText();
        filial = botaoFilial.getText();
        alunos = numAlunosTexto.getText();
        //System.out.println(codigo);System.out.println(filial);System.out.println(alunos);
        if (!testaLong(codigo, 0l, Long.MAX_VALUE)) {
            new Alert(Alert.AlertType.INFORMATION, "O código deve ser um número positivo").show();
            return false;
        } else if (filial.trim().toLowerCase().equals("Escolher Filial".trim().toLowerCase()) || !testaString(filial)) {
            new Alert(Alert.AlertType.INFORMATION, "Escolha uma filial").show();
            return false;
        } else if (!testaInt(alunos, 10, 50)) {
            new Alert(Alert.AlertType.INFORMATION, "O número de alunos deve ser preenchido com um inteiro entre 10 e 50").show();
            return false;
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ASDASDADS");
        new Thread(new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<String> result = new ArrayList<String>();
                ResultSet r = DBHelper.getInstance().select("Filial", "nome", true, null, null, null, null, "nome asc", null);
                while (r.next()) {
                    result.add(r.getString("nome"));
                }
                return result;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                try {
                    MenuItem m;
                    for (String s : get()) {
                        m = new MenuItem(s);
                        m.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                botaoFilial.setText(((MenuItem) event.getSource()).getText());
                            }
                        });
                        botaoFilial.getItems().add(m);
                        System.out.println("Add " + s);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
     /* Clicando no botão da Filial, abre uma pop-up com as informações das Filiais cadastradas e o usuário pode escolher
        uma. */
    /* Clicando no botão Salvar salva no BD e clicando no botão Voltar volta pra outra tela rs */
}

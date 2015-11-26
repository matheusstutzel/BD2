package telas.Super;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Matheus on 22/11/2015.
 */
public abstract class NewThing extends Super implements Initializable {
    public Button botaoSalvar;
    public Button botaoVoltar;

    public void salva(ActionEvent actionEvent) {
        if (insere()) sair();
    }

    public boolean insere() {
        try {
            testaInformacoes();
            return insert(getTableName(), getParametros());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro! " + e.getMessage()).show();
        }
        return false;
    }

    protected String getTableName() {
        return this.getClass().getSimpleName();
    }

    protected abstract void testaInformacoes();

    protected abstract HashMap<String, String> getParametros();

    public void volta(ActionEvent actionEvent) {
        sair();
    }

    private void sair() {
        ((Stage) botaoSalvar.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    protected boolean insert(String turma, HashMap<String, String> stringStringHashMap) {
        try {
            return DBHelper.getInstance().insert(turma, stringStringHashMap);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao adicionar " + e.getMessage());
            return false;
        }
    }

    public interface ItemClickListener {
        void onAction(HashMap<String, Object> s);
    }
}

package telas;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Matheus on 22/11/2015.
 */
public abstract class NewThing implements Initializable {
    public Button botaoSalvar;
    public Button botaoVoltar;

    public void salva(ActionEvent actionEvent) {
        if (insere()) sair();
    }

    abstract boolean insere();

    public void volta(ActionEvent actionEvent) {
        sair();
    }

    private void sair() {
    }

    public boolean testaString(String s) {
        return testaString(s, 1);
    }

    public boolean testaString(String s, int min) {
        return s != null && s.trim().length() >= min;
    }

    public boolean testaInt(String s, int min, int max) {
        if (s == null || s.trim().length() < 1) return false;
        try {
            int d = Integer.parseInt(s.trim());
            if (d < min || d > max) return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean testaFloat(String s, int min, int max) {
        if (s == null || s.trim().length() < 1) return false;
        try {
            float d = Float.parseFloat(s.trim());
            if (d < min || d > max) return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean testaLong(String s, long min, long max) {
        if (s == null || s.trim().length() < 1) return false;
        try {
            long d = Long.parseLong(s.trim());
            if (d < min || d > max) return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

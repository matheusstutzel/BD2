package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

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

    protected void setMenuButton(final MenuButton mb, final String table, final String column) {
        new Thread(new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<String> result = new ArrayList<String>();
                ResultSet r = DBHelper.getInstance().select(table, column, true, null, null, null, null, column + " asc", null);
                while (r.next()) {
                    result.add(r.getString(column));
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
                                mb.setText(((MenuItem) event.getSource()).getText());
                            }
                        });
                        mb.getItems().add(m);
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
}

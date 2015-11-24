package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
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

    abstract void testaInformacoes();

    abstract HashMap<String, String> getParametros();

    public void volta(ActionEvent actionEvent) {
        sair();
    }

    private void sair() {
        ((Stage) botaoSalvar.getScene().getWindow()).close();
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

    protected void setMenuButton(final MenuButton mb, final String table, final String column, final ItemClickListener handler) {
        new Thread(new Task<ArrayList<HashMap<String, Object>>>() {
            @Override
            protected ArrayList<HashMap<String, Object>> call() throws Exception {
                ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
                ResultSet r = DBHelper.getInstance().select(table, column, true, null, null, null, null, column + " asc", null);
                String[] c = column.split(",");
                HashMap<String, Object> t;
                while (r.next()) {
                    t = new HashMap<String, Object>();
                    for (String s : c) {
                        t.put(s, r.getObject(s));
                    }
                    result.add(t);
                }
                return result;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                try {
                    MenuItem m;
                    for (final HashMap<String, Object> s : get()) {
                        m = new MenuItem(s.get(column.split(",")[0]).toString());
                        m.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                mb.setText(((MenuItem) event.getSource()).getText());
                                handler.onAction(s);
                            }
                        });
                        mb.getItems().add(m);
                        System.out.println("Add " + m.getText());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

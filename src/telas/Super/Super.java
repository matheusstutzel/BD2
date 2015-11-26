package telas.Super;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ameix on 26/11/2015.
 */
public class Super {


    protected void setMenuButton(final MenuButton mb, final String table, final String column, final NewThing.ItemClickListener handler) {
        setMenuButton(mb, table, column, handler, true);
    }

    protected void setMenuButton(final MenuButton mb, final String table, final String column, final NewThing.ItemClickListener handler, boolean convert) {
        final String sql = (convert) ? DBHelper.getInstance().convertSelect(table, column, true, null, null, null, null, column + " asc", null) : table;
        new Thread(new Task<ArrayList<HashMap<String, Object>>>() {
            @Override
            protected ArrayList<HashMap<String, Object>> call() throws Exception {
                ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
                ResultSet r = DBHelper.getInstance().select(sql);
                String[] c = column.split(",");
                HashMap<String, Object> t;
                while (r.next()) {
                    t = new HashMap<String, Object>();
                    for (String s : c) {
                        t.put(s, r.getObject(s));
                    }
                    System.out.println(t);
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

}

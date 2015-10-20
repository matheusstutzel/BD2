package sample;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by Matheus on 15/10/2015.
 */
public class Teste {
    public Label txt;
    public TextField textField;

    public void sayHelloWorld(ActionEvent actionEvent) {
        txt.setText("Executando select");
        new Thread(new Task<String>() {
            @Override
            protected String call() throws Exception {
                String t = textField.getText();
                StringBuilder b = new StringBuilder();
                ResultSet resultSet = null;
                resultSet = DBHelper.getInstance().select(t, false, null, null, null, null, null, "10");
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (resultSet.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) b.append(",  ");
                        String columnValue = resultSet.getString(i);
                        b.append(rsmd.getColumnName(i)).append(": ").append(columnValue);
                    }
                    b.append("\n");
                }
                return b.toString();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                txt.setText(getValue());
            }

            @Override
            protected void failed() {
                super.failed();
                txt.setText(getException().getMessage());
            }
        }).start();
    }
}

package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Created by ameix on 06/12/2015.
 */
public class VerListaAlunos implements Initializable {
    public Label nomeTabela;
    public TableView table;
    public Button sair;
    private ObservableList<ObservableList> data;
    private ObservableList<Object> column;
    private String cod_filial;

    public void sair(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(String cod) {
        cod_filial = cod;
        //System.out.println(matricula);
        System.out.println("Teste");
        new Thread(new Task<String>() {
            @Override
            protected String call() throws Exception {
                //StringBuilder b = getTableText(t);
                getFilial();
                return "null";//b.toString();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                updateData();
            }

            @Override
            protected void failed() {
                super.failed();
            }
        }).start();
    }

    private void updateData() {
        table.getColumns().addAll(column);
        //FINALLY ADDED TO TableView
        table.setItems(data);
    }

    private void getFilial() {
        Connection c;
        data = FXCollections.observableArrayList();
        column = FXCollections.observableArrayList();
        try {
            c = DBHelper.getInstance().connection;
            String SQL = "SELECT * from Aluno, Vinculado where Vinculado.matricula_aluno=Aluno.matricula_aluno and cod_filial=" + cod_filial;
            ResultSet rs = c.createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                column.addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}

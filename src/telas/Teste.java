package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Matheus on 15/10/2015.
 */
public class Teste {
    public TableView table;
    public MenuButton menuTabela;
    public Label nomeTabela;
    public Button botaoAdicionar;
    public Button botaoExcluir;
    public Button sair;

    /*Na combobox tem o nome de todas as tabelas principais. Ao mudar carregar
        todas as linhas para a tabela e ao clicar em uma linha mostrar todos os relacionamentos
            daquela tabela com as outras (Disponivel, Inscrito, Leciona, Marcado, Ministrar, Vinculado).
      O botão de - tira a linha da tabela, confirmando antes (pop up tbm) e o botão de + redireciona a tela
        de adicionar novo, uma pra cada tabela (adicionarTabela.fxml)
     */

    private StringBuilder getTableText(String t) throws SQLException {
        StringBuilder b = new StringBuilder();
        new StringBuilder();
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
        return b;
    }

    //CONNECTION DATABASE
    public void buildData(String t) {
        Connection c;
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {
            c = DBHelper.getInstance().connection;
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from " + t;
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                table.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
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

            //FINALLY ADDED TO TableView
            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void selectTable(ActionEvent actionEvent) {
        System.out.println("Teste");
        final MenuItem m = ((MenuItem) actionEvent.getSource());
        nomeTabela.setText(m.getText());
        new Thread(new Task<String>() {
            @Override
            protected String call() throws Exception {
                //StringBuilder b = getTableText(t);
                buildData(m.getText());
                return "null";//b.toString();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
            }

            @Override
            protected void failed() {
                super.failed();
            }
        }).start();
    }

    public void sair(ActionEvent actionEvent) {
        Main.s.close();
    }
}

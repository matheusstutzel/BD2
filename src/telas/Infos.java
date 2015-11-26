package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Matheus on 26/11/2015.
 */
public class Infos {
    public TableView table;
    public Label nomeTabela;
    public Button botaoAdicionar;
    public Button botaoExcluir;
    public Button sair;
    private ObservableList<ObservableList> data;
    private ObservableList<Object> column;
    private int tipo = -1;

    public void init(final String sql) {
        clearTable();
        new Thread(new Task<String>() {
            @Override
            protected String call() throws Exception {
                //StringBuilder b = getTableText(t);
                buildData(sql);
                return "null";//b.toString();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                table.getColumns().addAll(column);
                //FINALLY ADDED TO TableView
                table.setItems(data);
            }

            @Override
            protected void failed() {
                super.failed();
            }
        }).start();
    }

    private void clearTable() {
        ObservableList t = table.getItems();
        ArrayList o = new ArrayList();
        for (Object temp : t) {
            o.add(temp);
        }
        t.removeAll(o);

        t = table.getColumns();
        o = new ArrayList();
        for (Object temp : t) {
            o.add(temp);
        }
        t.removeAll(o);

    }

    public void buildData(String sql) {
        Connection c;
        data = FXCollections.observableArrayList();
        column = FXCollections.observableArrayList();
        try {
            c = DBHelper.getInstance().connection;
            ResultSet rs = c.createStatement().executeQuery(sql);

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

    public void delete(ActionEvent actionEvent) {
        ObservableList t = table.getItems();
        String delete = null;
        if (table.getSelectionModel().getSelectedCells().size() < 1)
            new Alert(Alert.AlertType.WARNING, "Selecione uma linha da tabela abaixo").show();
        else for (Object o : table.getSelectionModel().getSelectedCells()) {
            if (tipo == 0) {
                ArrayList<String> row = new ArrayList<String>();
                row.add(((ObservableList) t.get(((TablePosition) o).getRow())).get(0).toString());
                row.add(((ObservableList) t.get(((TablePosition) o).getRow())).get(2).toString());
                ArrayList<String> colunas = new ArrayList<String>();
                colunas.add(((TableColumn) (column).get(0)).getText());
                colunas.add(((TableColumn) (column).get(2)).getText());
                delete = DBHelper.criaDelete(nomeTabela.getText(), row, colunas);
                System.out.println(delete);
            }
            try {
                if (DBHelper.getInstance().delete(delete))
                    t.remove(((TablePosition) o).getRow());
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Erro ao deletar item," + e.getMessage()).show();
                e.printStackTrace();
            }
        }
    }

    public void insert(ActionEvent actionEvent) {
        createDialogInsert(nomeTabela.getText(), actionEvent);
    }

    public void createDialogInsert(String tableName, ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    Teste.class.getResource("adicionar" + tableName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null) {
            stage.setScene(new Scene(root));
        }
        stage.setTitle(tableName);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void sair(ActionEvent actionEvent) {
        ((Stage) sair.getScene().getWindow()).close();
    }

    public void initTurma(String mat) {
        init("Select a.matricula_aluno,a.nome,t.cod_turma,t.num_sala,f.nome,m.nome from Aluno a Join Inscrito i on (i.matricula_aluno=a.matricula_aluno) join Turma t on (t.cod_turma = i.cod_turma)" +
                "join Materia m on(m.cod_materia=t.cod_materia) join Filial f on (f.cod_filial=t.cod_filial) where a.matricula_aluno=" + mat);
        nomeTabela.setText("Inscrito");
        tipo = 0;
    }
}

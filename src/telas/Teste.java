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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private ObservableList<ObservableList> data;
    private ObservableList<Object> column;

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
        data = FXCollections.observableArrayList();
        column = FXCollections.observableArrayList();
        try {
            c = DBHelper.getInstance().connection;
            String SQL = "SELECT * from " + t;
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

    public void selectTable(ActionEvent actionEvent) {
        System.out.println("Teste");
        final MenuItem m = ((MenuItem) actionEvent.getSource());
        nomeTabela.setText(m.getText());
        clearTable();
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

    public void sair(ActionEvent actionEvent) {
        Main.s.close();
    }

    public void delete(ActionEvent actionEvent) {
        ObservableList t = table.getItems();
        String delete;
        if (table.getSelectionModel().getSelectedCells().size() < 1)
            new Alert(Alert.AlertType.WARNING, "Selecione uma linha da tabela abaixo").show();
        else for (Object o : table.getSelectionModel().getSelectedCells()) {
            delete = DBHelper.criaDelete(nomeTabela.getText(), (ObservableList) t.get(((TablePosition) o).getRow()), column);
            System.out.println(delete);
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
        String tabelaNome = nomeTabela.getText();
        createDialogInsert(tabelaNome, actionEvent);
    }

    public void createDialogInsert(String tableName, ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        if (tableName.equals("Aluno")) {
            try {
                root = FXMLLoader.load(
                        Aluno.class.getResource("adicionarAluno.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableName.equals("Filial")) {
            try {
                root = FXMLLoader.load(
                        Filial.class.getResource("adicionarFilial.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableName.equals("Horario")) {
            try {
                root = FXMLLoader.load(
                        Horario.class.getResource("adicionarHorario.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableName.equals("Materia")) {
            try {
                root = FXMLLoader.load(
                        Materia.class.getResource("adicionarMateria.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableName.equals("Professor")) {
            try {
                root = FXMLLoader.load(
                        Professor.class.getResource("adicionarProfessor.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableName.equals("Sala")) {
            try {
                root = FXMLLoader.load(
                        Sala.class.getResource("adicionarSala.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tableName.equals("Turma")) {
            try {
                root = FXMLLoader.load(
                        Turma.class.getResource("adicionarTurma.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(new Scene(root));
        stage.setTitle(tableName);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

    public void maisOpcoes(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION, "Essa lista não possui nenhuma opção extra").show();
    }
}

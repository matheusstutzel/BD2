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
import javafx.scene.layout.Pane;
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
    public MenuButton botaoMaisOpcoes;
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
        menuTabela.setText(m.getText());
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
        createDialogInsert(nomeTabela.getText(), actionEvent);
    }

    public void createDialogInsert(String tableName, ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(
                    Teste.class.getResource("../fxml/adicionar" + tableName + ".fxml"));
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

    public void verEscolherFilial(ActionEvent actionEvent) {
        ObservableList t = table.getItems();
        if (nomeTabela.getText().equals("Aluno") && table.getSelectionModel().getSelectedCells().size() == 1) {
            String aux = null;
            for (Object o : table.getSelectionModel().getSelectedCells()) {
                aux = ((ObservableList) t.get(((TablePosition) o).getRow())).get(0).toString();
            }
            Stage stage = new Stage();
            FXMLLoader root = null;
            try {
                root = new FXMLLoader(
                        Teste.class.getResource("../fxml/verEditarFilial.fxml"));
                stage.setScene(new Scene((Pane) root.load()));
                stage.setTitle("Ver/Escolher Filial");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(botaoMaisOpcoes.getScene().getWindow());
                VerEditarFilial controller =
                        root.<VerEditarFilial>getController();
                controller.initData(aux);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else new Alert(Alert.AlertType.WARNING, "Selecione uma linha da tabela abaixo").show();
    }

    public void verEscolherTurma(ActionEvent actionEvent) {
        if (table.getSelectionModel().getSelectedCells().size() < 1)
            new Alert(Alert.AlertType.WARNING, "Selecione uma linha da tabela abaixo").show();
        else {
            Stage stage = new Stage();
            FXMLLoader root = null;
            try {
                root = new FXMLLoader(
                        Teste.class.getResource("../fxml/verInfo.fxml"));
                stage.setScene(new Scene((Pane) root.load()));
                stage.setTitle("Infos");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(botaoMaisOpcoes.getScene().getWindow());
                Infos controller =
                        root.<Infos>getController();
                Object o = table.getSelectionModel().getSelectedCells().get(0);
                controller.initTurma(((ObservableList) data.get(((TablePosition) o).getRow())).get(0).toString());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void verGradeHorarios(ActionEvent actionEvent) {
        ObservableList t = table.getItems();
        if (nomeTabela.getText().equals("Aluno") && table.getSelectionModel().getSelectedCells().size() == 1) {
            String aux = null;
            for (Object o : table.getSelectionModel().getSelectedCells()) {
                aux = ((ObservableList) t.get(((TablePosition) o).getRow())).get(0).toString();
            }
            Stage stage = new Stage();
            FXMLLoader root = null;
            try {
                root = new FXMLLoader(
                        Teste.class.getResource("../fxml/alunoGrade.fxml"));
                stage.setScene(new Scene((Pane) root.load()));
                stage.setTitle("Grade de horário");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(botaoMaisOpcoes.getScene().getWindow());
                AlunoGrade controller =
                        root.<AlunoGrade>getController();
                controller.initData(aux);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (nomeTabela.getText().equals("Professor") && table.getSelectionModel().getSelectedCells().size() == 1) {
            String aux = null;
            for (Object o : table.getSelectionModel().getSelectedCells()) {
                aux = ((ObservableList) t.get(((TablePosition) o).getRow())).get(0).toString();
            }
            Stage stage = new Stage();
            FXMLLoader root = null;
            try {
                root = new FXMLLoader(
                        Teste.class.getResource("../fxml/professorGrade.fxml"));
                stage.setScene(new Scene((Pane) root.load()));
                stage.setTitle("Grade de horário");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(botaoMaisOpcoes.getScene().getWindow());
                ProfessorGrade controller =
                        root.<ProfessorGrade>getController();
                controller.initData(aux);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else new Alert(Alert.AlertType.WARNING, "Selecione uma linha da tabela abaixo").show();
    }

    public void maisInfos(ActionEvent actionEvent) {
    }

}

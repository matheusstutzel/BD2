package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by ameix on 26/11/2015.
 */
public class VerEditarFilial implements Initializable {
    public Label filialName;
    public Button btAddEdit;
    String matricula, cod_filial;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(String mat) {
        matricula = mat;
        getFilial();
        //System.out.println(matricula);
    }

    private void getFilial() {
        try {
            ResultSet rs = DBHelper.getInstance().connection.createStatement().executeQuery("Select Filial.cod_filial, Filial.nome From Filial, Vinculado Where Vinculado.cod_filial=Filial.cod_filial and matricula_aluno=" + matricula);
            String nome = null;
            while (rs.next()) {
                cod_filial = rs.getString("cod_filial");
                nome = rs.getString("nome");
                System.out.println(cod_filial + " - " + nome);
            }
            filialName.setText(cod_filial == null ? " - " : (cod_filial + "\n" + nome));
            if (cod_filial == null) btAddEdit.setText("Vincular à uma Filial");
            else btAddEdit.setText("Mudar Filial");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEditFilial(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader root = null;
        try {
            root = new FXMLLoader(
                    Teste.class.getResource("../fxml/addEditarFilial.fxml"));
            stage.setScene(new Scene((Pane) root.load()));
            stage.setTitle("Editar Filial");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btAddEdit.getScene().getWindow());
            AddEditarFilial controller =
                    root.<AddEditarFilial>getController();
            controller.initData(btAddEdit.getText(), matricula);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    getFilial();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

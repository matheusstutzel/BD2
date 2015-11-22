package telas;

import br.uerj.bd2_2015_2.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;

public class Controller {
    public Label helloWorld;
    public PasswordField PASSWORD;
    public TextField USER;
    public TextField BD;
    public TextField host;

    public void sayHelloWorld(ActionEvent event) throws IOException {
        if (!testaArgumento()) new Alert(Alert.AlertType.ERROR, "Erro nos dados, tente novamente").show();
        else if (testaConexao()) {
            Parent root = FXMLLoader.load(getClass().getResource("teste.fxml"));
            Main.s.setScene(new Scene(root, 800, 600));
        } else new Alert(Alert.AlertType.ERROR, "Erro na conexão do Banco de Dados, tente novamente").show();
    }

    private boolean testaConexao() {
        Connection c = DBHelper.getInstance().getConnection(host.getText(), BD.getText(), USER.getText(), PASSWORD.getText());
        return c != null && verificaTabelas(c);
    }

    private boolean verificaTabelas(Connection c) {
        //todo Verificar se existem tabelas else cria as tabelas
        return true;
    }

    private boolean testaArgumento() {
        //host.getText(), BD.getText(), USER.getText(), PASSWORD.getText())
        return host.getText().length() > 0 && BD.getText().length() > 0 && USER.getText().length() > 0 && PASSWORD.getText().length() > 0;
    }

}

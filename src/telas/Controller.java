package telas;

import br.uerj.bd2_2015_2.DBConfig;
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
import java.sql.SQLException;

public class Controller {
    public Label helloWorld;
    public PasswordField PASSWORD;
    public TextField USER;
    public TextField BD;
    public TextField host;

    public void sayHelloWorld(ActionEvent event) throws IOException {
        if (!testaArgumento()) new Alert(Alert.AlertType.ERROR, "Erro nos dados, tente novamente").show();
        else if (testaConexao()) {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/teste.fxml"));
            Main.s.setScene(new Scene(root, 800, 600));
        } else new Alert(Alert.AlertType.ERROR, "Erro na conexão do Banco de Dados, tente novamente").show();
    }

    private boolean testaConexao() {
        Connection c = DBHelper.getInstance().getConnection(host.getText(), BD.getText(), USER.getText(), PASSWORD.getText());
        return c != null && verificaTabelas(c);
    }

    private boolean verificaTabelas(Connection c) {
        try {
            c.createStatement().executeQuery("SELECT * FROM Aluno");
            c.createStatement().executeQuery("SELECT * FROM Professor");
        } catch (SQLException e) {
            try {
                c.createStatement().executeQuery(DBConfig.createFilial);
                c.createStatement().executeQuery(DBConfig.createAluno);
                c.createStatement().executeQuery(DBConfig.createSala);
                c.createStatement().executeQuery(DBConfig.createVinculado);
                c.createStatement().executeQuery(DBConfig.createMateria);
                c.createStatement().executeQuery(DBConfig.createTurma);
                c.createStatement().executeQuery(DBConfig.createInscrito);
                c.createStatement().executeQuery(DBConfig.createHorario);
                c.createStatement().executeQuery(DBConfig.createMarcado);
                c.createStatement().executeQuery(DBConfig.createProfessor);
                c.createStatement().executeQuery(DBConfig.createDisponivel);
                c.createStatement().executeQuery(DBConfig.createMinistrar);
                c.createStatement().executeQuery(DBConfig.createLeciona);
                c.createStatement().executeQuery(DBConfig.triggerAttTurma);
                c.createStatement().executeQuery(DBConfig.triggerAttTurmaLog);
                c.createStatement().executeQuery(DBConfig.triggerDelTurmaLog);
                c.createStatement().executeQuery(DBConfig.triggerInsertTurmaLog);
                c.createStatement().executeQuery(DBConfig.functionMensalidadeJuros);
                c.createStatement().executeQuery(DBConfig.functionSalario);
                c.createStatement().executeQuery(DBConfig.functionMateriasAluno);
                c.createStatement().executeQuery(DBConfig.procedureInsereMinistrar);
                c.createStatement().executeQuery(DBConfig.procedureInsereInscrito);
                c.createStatement().executeQuery(DBConfig.procedureInsereTurma);
                c.createStatement().executeQuery(DBConfig.insertFilial);
                c.createStatement().executeQuery(DBConfig.insertSala);
                c.createStatement().executeQuery(DBConfig.insertAluno);
                c.createStatement().executeQuery(DBConfig.insertVinculado);
                c.createStatement().executeQuery(DBConfig.insertMateria);
                c.createStatement().executeQuery(DBConfig.insertHorario);
                c.createStatement().executeQuery(DBConfig.insertProfessor);
                c.createStatement().executeQuery(DBConfig.insertDisponivel);
                c.createStatement().executeQuery(DBConfig.insertLeciona);
                c.createStatement().executeQuery(DBConfig.insertTurma);
                c.createStatement().executeQuery(DBConfig.insertInscrito);
                c.createStatement().executeQuery(DBConfig.insertMinistrar);
                c.createStatement().executeQuery(DBConfig.insertMarcado);
                c.createStatement().executeQuery(DBConfig.viewAlunoFilial);
                c.createStatement().executeQuery(DBConfig.viewAlunoMateria);
                c.createStatement().executeQuery(DBConfig.viewAlunoMensalidade);
                c.createStatement().executeQuery(DBConfig.viewLeciona);
                c.createStatement().executeQuery(DBConfig.viewProfessorInfo);
                c.createStatement().executeQuery(DBConfig.viewProfessorSalario);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return true;
    }

    private boolean testaArgumento() {
        //host.getText(), BD.getText(), USER.getText(), PASSWORD.getText())
        return host.getText().length() > 0 && BD.getText().length() > 0 && USER.getText().length() > 0 && PASSWORD.getText().length() > 0;
    }

}

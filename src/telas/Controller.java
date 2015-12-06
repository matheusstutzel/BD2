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
import java.sql.Statement;

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
                c.createStatement().execute(DBConfig.createFilial);
                c.createStatement().execute(DBConfig.createAluno);
                c.createStatement().execute(DBConfig.createSala);
                c.createStatement().execute(DBConfig.createVinculado);
                c.createStatement().execute(DBConfig.createMateria);
                c.createStatement().execute(DBConfig.createTurma);
                c.createStatement().execute(DBConfig.createInscrito);
                c.createStatement().execute(DBConfig.createHorario);
                c.createStatement().execute(DBConfig.createMarcado);
                c.createStatement().execute(DBConfig.createProfessor);
                c.createStatement().execute(DBConfig.createDisponivel);
                c.createStatement().execute(DBConfig.createMinistrar);
                c.createStatement().execute(DBConfig.createLeciona);
                c.createStatement().execute(DBConfig.triggerAttTurma);
                c.createStatement().execute(DBConfig.triggerAttTurmaLog);
                c.createStatement().execute(DBConfig.triggerDelTurmaLog);
                c.createStatement().execute(DBConfig.triggerInsertTurmaLog);
                c.createStatement().execute(DBConfig.functionMensalidadeJuros);
                c.createStatement().execute(DBConfig.functionSalario);
                c.createStatement().execute(DBConfig.functionMateriasAluno);
                c.createStatement().execute(DBConfig.procedureInsereMinistrar);
                c.createStatement().execute(DBConfig.procedureInsereInscrito);
                c.createStatement().execute(DBConfig.procedureInsereTurma);
                String[][] aux = new String[][]{
                        DBConfig.insertFilial,
                        DBConfig.insertSala,
                        DBConfig.insertAluno,
                        DBConfig.insertVinculado,
                        DBConfig.insertMateria,
                        DBConfig.insertHorario,
                        DBConfig.insertProfessor,
                        DBConfig.insertDisponivel,
                        DBConfig.insertLeciona,
                        DBConfig.insertTurma,
                        DBConfig.insertInscrito,
                        DBConfig.insertMinistrar,
                        DBConfig.insertMarcado
                };
                Statement stm = c.createStatement();
                stm.clearBatch();
                for (String[] strings : aux) {
                    for (String s : strings) {
                        stm.addBatch(s);
                    }
                }
                stm.executeBatch();
                c.createStatement().execute(DBConfig.viewAlunoFilial);
                c.createStatement().execute(DBConfig.viewAlunoMateria);
                c.createStatement().execute(DBConfig.viewAlunoMensalidade);
                c.createStatement().execute(DBConfig.viewLeciona);
                c.createStatement().execute(DBConfig.viewProfessorInfo);
                c.createStatement().execute(DBConfig.viewProfessorSalario);
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

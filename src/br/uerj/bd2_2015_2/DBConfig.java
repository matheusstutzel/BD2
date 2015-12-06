package br.uerj.bd2_2015_2;

/**
 * Created by ameix on 24/11/2015.
 */
public class DBConfig {
    final public static String createFilial = "CREATE TABLE Filial (\n" +
            "\tcod_filial INT(10) NOT NULL,\n" +
            "\tendereco VARCHAR(50) NOT NULL,\n" +
            "\tnome VARCHAR(50) NOT NULL ,\n" +
            "\tPRIMARY KEY(cod_filial)\n" +
            ");";
    final public static String createAluno = "CREATE TABLE Aluno (\n" +
            "\tmatricula_aluno INT(10) NOT NULL,\n" +
            "\tnome VARCHAR(50) NOT NULL ,\n" +
            "\tdesconto REAL(5,2) NULL ,\n" +
            "\tendereco VARCHAR(50) NULL ,\n" +
            "\tturno VARCHAR(20) NOT NULL ,\n" +
            "\tmensalidadeBasica REAL(6,2) NOT NULL,\n" +
            "\tPRIMARY KEY (matricula_aluno), \n" +
            "\tCHECK(desconto<=50),\n" +
            "\tCHECK(turno in (\"Manhã\",\"Tarde\",\"Noite\"))\n" +
            ");";
    final public static String createSala = "CREATE TABLE Sala (\n" +
            "\tnum_sala INT(2) NOT NULL ,\n" +
            "\tcod_filial INT(10) NOT NULL ,\n" +
            "\tnumMaxAlunos INT(4) NOT NULL ,\n" +
            "\tPRIMARY KEY (num_sala, cod_filial)  ,\n" +
            "\tCONSTRAINT fk_sala_filial_cod_filial\n" +
            "\t\tFOREIGN KEY (cod_filial)\n" +
            "\t\tREFERENCES Filial (cod_filial)\n" +
            ");";
    final public static String createVinculado = "CREATE TABLE Vinculado (\n" +
            "\tcod_filial INT(10) NOT NULL ,\n" +
            "\tmatricula_aluno INT(10) NOT NULL ,\n" +
            "\tPRIMARY KEY (cod_filial, matricula_aluno)  ,\n" +
            "\tCONSTRAINT fk_vinculado_aluno\n" +
            "\t\tFOREIGN KEY (matricula_aluno)\n" +
            "\t\tREFERENCES Aluno (matricula_aluno),\n" +
            "\tCONSTRAINT fk_vinculado_filial\n" +
            "\t\tFOREIGN KEY (cod_filial)\n" +
            "\t\tREFERENCES Filial (cod_filial)\n" +
            ");";
    final public static String createMateria = "CREATE TABLE Materia (\n" +
            "\tcod_materia INT(10) NOT NULL ,\n" +
            "\thorasPorSemana INT(2) NOT NULL DEFAULT 2,\n" +
            "\tmensalidade REAL(6,2) NOT NULL DEFAULT 100,\n" +
            "\tnome VARCHAR(50) NOT NULL ,\n" +
            "\tPRIMARY KEY (cod_materia),\n" +
            "\tCHECK(horasPorSemana<=6),\n" +
            "\tCHECK(mensalidade<=200)\n" +
            ");";
    final public static String createTurma = "CREATE TABLE Turma (\n" +
            "\tcod_turma INT(10) NOT NULL ,\n" +
            "\tnum_sala INT(10) NULL ,\n" +
            "\tcod_filial INT(10) NULL ,\n" +
            "\tcod_materia INT(10) NOT NULL ,\n" +
            "\tnumMaxAlunos INT(2) NULL ,\n" +
            "\tano INT(4) NOT NULL ,\n" +
            "\tPRIMARY KEY (cod_turma)  ,\n" +
            "\tCONSTRAINT fk_turma_sala\n" +
            "\t\tFOREIGN KEY (num_sala, cod_filial)\n" +
            "\t\tREFERENCES Sala (num_sala, cod_filial),\n" +
            "\tCONSTRAINT fk_turma_materia_cod_materia\n" +
            "\t\tFOREIGN KEY (cod_materia)\n" +
            "\t\tREFERENCES Materia (cod_materia),\n" +
            "\tCHECK(numMaxAlunos>=10),\n" +
            "\tCHECK(numMaxAlunos<=50)\n" +
            ");";
    final public static String createInscrito = "CREATE TABLE Inscrito (\n" +
            "\tmatricula_aluno INT(10) NOT NULL ,\n" +
            "\tcod_turma INT(10) NOT NULL ,\n" +
            "\tPRIMARY KEY (matricula_aluno, cod_turma)  ,\n" +
            "\tCONSTRAINT fk_inscrito_aluno\n" +
            "\t\tFOREIGN KEY (matricula_aluno)\n" +
            "\t\tREFERENCES Aluno (matricula_aluno),\n" +
            "\tCONSTRAINT fk_inscrito_turma\n" +
            "\t\tFOREIGN KEY (cod_turma)\n" +
            "\t\tREFERENCES Turma (cod_turma)\n" +
            ");";
    final public static String createHorario = "CREATE TABLE Horario (\n" +
            "\tdia INT(1) NOT NULL ,\n" +
            "\tinicio INT(2) NOT NULL ,\n" +
            "\tPRIMARY KEY (dia, inicio),\n" +
            "\tCHECK(dia<7),\n" +
            "\tCHECK(dia>=1),\n" +
            "\tCHECK(inicio>=8),\n" +
            "\tCHECK(inicio<=21),\n" +
            "\tCHECK(inicio!=12),\n" +
            "\tCHECK(inicio!=17)\n" +
            ");";
    final public static String createMarcado = "CREATE TABLE Marcado (\n" +
            "\tcod_turma INT(10) NOT NULL ,\n" +
            "\tdia INT(1) NOT NULL ,\n" +
            "\tinicio INT(2) NOT NULL ,\n" +
            "\tPRIMARY KEY (cod_turma, dia, inicio)  ,\n" +
            "\tCONSTRAINT fk_marcado_turma_cod_turma\n" +
            "\t\tFOREIGN KEY (cod_turma)\n" +
            "\t\tREFERENCES Turma (cod_turma),\n" +
            "\tCONSTRAINT fk_marcado_horario\n" +
            "\t\tFOREIGN KEY (dia, inicio)\n" +
            "\t\tREFERENCES Horario (dia, inicio)\n" +
            ");";
    final public static String createProfessor = "CREATE TABLE Professor (\n" +
            "\tmatricula_professor INT(10) NOT NULL ,\n" +
            "\thoraAula REAL(5,2) NOT NULL ,\n" +
            "\tendereco VARCHAR(50) NOT NULL ,\n" +
            "\tnome VARCHAR(50) NOT NULL ,\n" +
            "\tPRIMARY KEY (matricula_professor),\n" +
            "\tCHECK(horaAula<=500.00) \n" +
            ");";
    final public static String createDisponivel = "CREATE TABLE Disponivel (\n" +
            "\tmatricula_professor INT(10) NOT NULL ,\n" +
            "\tdia INT(1) NOT NULL ,\n" +
            "\tinicio INT(2) NOT NULL ,\n" +
            "\tPRIMARY KEY (matricula_professor, dia, inicio)  ,\n" +
            "\tCONSTRAINT fk_marcado_professor_matricula_professor\n" +
            "\t\tFOREIGN KEY (matricula_professor)\n" +
            "\t\tREFERENCES Professor (matricula_professor),\n" +
            "\tCONSTRAINT fk_disponivel_horario\n" +
            "\t\tFOREIGN KEY (dia, inicio)\n" +
            "\t\tREFERENCES Horario (dia, inicio)\n" +
            ");";
    final public static String createMinistrar = "CREATE TABLE Ministrar (\n" +
            "\tmatricula_professor INT(10) NOT NULL ,\n" +
            "\tcod_turma INT(10) NOT NULL ,\n" +
            "\tPRIMARY KEY (matricula_professor, cod_turma)  ,\n" +
            "\tCONSTRAINT fk_ministrar_professor\n" +
            "\t\tFOREIGN KEY (matricula_professor)\n" +
            "\t\tREFERENCES professor (matricula_professor),\n" +
            "\tCONSTRAINT fk_ministrar_turma\n" +
            "\t\tFOREIGN KEY (cod_turma)\n" +
            "\t\tREFERENCES Turma (cod_turma)\n" +
            ");";
    final public static String createLeciona = "CREATE TABLE Leciona (\n" +
            "\tmatricula_professor INT(10) NOT NULL ,\n" +
            "\tcod_materia INT(10) NOT NULL ,\n" +
            "\tPRIMARY KEY (matricula_professor, cod_materia)  ,\n" +
            "\tCONSTRAINT fk_lecionar_professor\n" +
            "\t\tFOREIGN KEY (matricula_professor)\n" +
            "\t\tREFERENCES Professor (matricula_professor),\n" +
            "\tCONSTRAINT fk_lecionar_materia\n" +
            "\t\tFOREIGN KEY (cod_materia)\n" +
            "\t\tREFERENCES Materia (cod_materia)\n" +
            ");";
    final public static String triggerAttTurma =
            "CREATE TRIGGER tg_atualiza_turma\n" +
            "BEFORE UPDATE ON Turma\n" +
            "FOR EACH ROW\n" +
            "BEGIN\n" +
            "\tDECLARE numAlunos INT(4);\n" +
            "    DECLARE numSala INT(10);\n" +
            "    DECLARE codigoFilial INT(10);\n" +
            "    DECLARE numAlunosTurma INT(10);\n" +
            "    SET numSala = NEW.num_sala;\n" +
            "    SET codigoFilial = NEW.cod_filial;\n" +
            "    SET numAlunosTurma = NEW.numMaxAlunos;\n" +
            "\tif numSala is null and codigoFilial is null then\n" +
            "\t\tif numAlunosTurma is null then\n" +
            "\t\t\tset NEW.numMaxAlunos = 50;\n" +
            "\t\tend if;\n" +
            "\telse\n" +
            "\t\tSET numAlunos = (SELECT numMaxAlunos From Sala Where num_sala = numSala and cod_filial = codigoFilial);\n" +
            "\t\tif numAlunosTurma is null or numAlunos < numAlunosTurma then\n" +
            "\t\t\tset NEW.numMaxAlunos = numAlunos;\n" +
            "\t\tEND if;\n" +
            "    END if;\n" +
                    "END;\n";
    final public static String triggerAttTurmaLog =
            "CREATE TRIGGER tg_atualiza_turma_log\n" +
            "AFTER UPDATE ON Turma\n" +
            "FOR EACH ROW\n" +
            "BEGIN\n" +
            "\tINSERT INTO Log_Turma(cod_turma, num_sala, cod_filial, cod_materia, numMaxAlunos, ano, usuario, operacao)\n" +
            "    VALUES ( OLD.cod_turma, OLD.num_sala, OLD.cod_filial, OLD.cod_materia, OLD.numMaxAlunos, OLD.ano, user(), 'UPD');\n" +
                    "END;\n";
    final public static String triggerDelTurmaLog =
            "CREATE TRIGGER tg_delete_turma_log\n" +
            "AFTER DELETE ON Turma\n" +
            "FOR EACH ROW\n" +
            "BEGIN\n" +
            "\tINSERT INTO Log_Turma(cod_turma, num_sala, cod_filial, cod_materia, numMaxAlunos, ano, usuario, operacao)\n" +
            "    VALUES ( OLD.cod_turma, OLD.num_sala, OLD.cod_filial, OLD.cod_materia, OLD.numMaxAlunos, OLD.ano, user(), 'DEL');\n" +
                    "END;\n";
    final public static String triggerInsertTurmaLog =
            "CREATE TRIGGER tg_insere_turma_log\n" +
            "AFTER INSERT ON Turma\n" +
            "FOR EACH ROW\n" +
            "BEGIN\n" +
            "\tINSERT INTO Log_Turma(cod_turma, num_sala, cod_filial, cod_materia, numMaxAlunos, ano, usuario, operacao)\n" +
            "    VALUES ( NEW.cod_turma, NEW.num_sala, NEW.cod_filial, NEW.cod_materia, NEW.numMaxAlunos, NEW.ano, user(), 'INS');\n" +
                    "END;\n";
    final public static String functionMensalidadeJuros =
            "CREATE FUNCTION calculaMensalidadeComJuros(mat numeric) RETURNS Real(10,2)\n" +
            "begin\n" +
            "DECLARE done INT DEFAULT FALSE;\n" +
            "declare total REAL(10,2);\n" +
            "declare codTurma INT(10);\n" +
            "declare desco REAL(5,2);\n" +
            "declare parcial REAL(10,2);\n" +
            "declare cont INT(10);\n" +
            "declare a INT(10);\n" +
            "declare b INT(10);\n" +
            "declare turnoAluno text;\n" +
            "declare c Cursor for select m.mensalidade,t.cod_turma from Inscrito i join Turma t on(t.cod_turma=i.cod_turma) join Materia m on (m.cod_materia=t.cod_materia)  where i.matricula_aluno=mat and t.ano=YEAR(CURDATE()) ;\n" +
            "declare d Cursor for select desconto, mensalidadeBasica,turno from Aluno where matricula_aluno=mat;\n" +
            "DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;\n" +
            "\n" +
            "open d;\n" +
            "FETCH d into desco,total,turnoAluno ;\n" +
            "close d;\n" +
            "open c;\n" +
            "\n" +
            "read_loop: LOOP\n" +
            "\tFETCH c into parcial,codTurma;\n" +
            "    \n" +
            "\tIF done THEN\n" +
            "      LEAVE read_loop;\n" +
            "    END IF;\n" +
            "\tif turnoAluno='Manhã'then \n" +
            "\t\tset a = 12;\n" +
            "        set b = 0;\n" +
            "\telseif turnoAluno='Tarde'then\n" +
            "\t\tset a = 17;\n" +
            "        set b = 13;\n" +
            "\telse\n" +
            "\t\tset a = 30;\n" +
            "        set b = 17;\n" +
            "\tEND IF;\n" +
            "\n" +
            "\tset cont  = (select count(*) from Marcado where cod_turma=codTurma and (inicio>a or inicio<b));\n" +
            "\tif cont>0 then \n" +
            "\t\tset total = total + (parcial*1.1);\n" +
            "\telse set total = total + parcial;\n" +
            "\tend if;\n" +
            "end loop;\n" +
            "close c;\n" +
            "return (1-(desco/100))*(total);\n" +
                    "end\n";
    final public static String functionSalario =
            "CREATE FUNCTION calculaSalario(mat numeric) RETURNS REAL(10,2)\n" +
            "begin\n" +
            "declare total REAL(10,2);\n" +
            "declare base REAL(6,2);\n" +
            "declare c Cursor for select sum(m.horasPorSemana) from Ministrar mi join Turma t on(t.cod_turma=mi.cod_turma) join Materia m on (m.cod_materia=t.cod_materia) where mi.matricula_professor=mat and t.ano=YEAR(CURDATE()) ;\n" +
            "declare d Cursor for select horaAula from Professor where matricula_professor=mat;\n" +
            "open c;\n" +
            "FETCH c into total;\n" +
            "close c;\n" +
            "open d;\n" +
            "FETCH d into base ;\n" +
            "close d;\n" +
            "if total is null then set total = 0 ; end if;\n" +
            "return 4*(total*base);\n" +
                    "end\n";
    final public static String functionMateriasAluno =
            "create FUNCTION materiasAluno(mat numeric) RETURNS text\n" +
            "begin\n" +
            "  DECLARE done INT DEFAULT FALSE;\n" +
            "  declare first1 int default  true;\n" +
            "declare materias text;\n" +
            "declare temp text;\n" +
            "declare c Cursor for select nome from Inscrito i join Turma t on(t.cod_turma=i.cod_turma) join Materia m on (m.cod_materia=t.cod_materia) where i.matricula_aluno=mat;\n" +
            "DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;\n" +
            "open c;\n" +
            "set materias = \"(\";\n" +
            "read_loop :LOOP\n" +
            "\n" +
            "\t\tFETCH c into temp;\n" +
            "    IF done then leave read_loop; end if;\n" +
            "    if first1 then\n" +
            "    \n" +
            "\t\tset materias = CONCAT(materias,temp);\n" +
            "\t\tset first1=false;\n" +
            "    \n" +
            "    else\n" +
            "\t\tset materias = CONCAT(materias,\", \",temp);\n" +
            "    end if;\n" +
            "    end loop;\n" +
            "close c;\n" +
            "return concat(materias,\")\");\n" +
            "\n" +
                    "end\n";
    final public static String procedureInsereMinistrar =
            "CREATE PROCEDURE insereMinistrar(IN codigoT numeric, IN matriculaP numeric, OUT result text)\n" +
            "BEGIN\n" +
            "DECLARE done INT DEFAULT FALSE;\n" +
            "declare mat Int(10);\n" +
            "declare achou bool default true;\n" +
            "declare achou2 bool default false;\n" +
            "declare achou3 bool default false;\n" +
            "declare aux Int(10);\n" +
            "declare str text default '';\n" +
            "declare str2 text default '';\n" +
            "declare str3 text default '';\n" +
            "declare professorHour INT(4); \n" +
            "declare turmaHour INT(4);\n" +
            "declare professorFilial INT(4);\n" +
            "declare professorMat CURSOR FOR Select m.cod_materia from Leciona l, Materia m where matriculaP = l.matricula_professor and l.cod_materia=m.cod_materia;\n" +
            "DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;\n" +
            "select m.cod_materia into mat from Materia m, Turma t where codigoT=t.cod_turma and t.cod_materia=m.cod_materia;\n" +
            "-- -------------------------------------------------------------------------------------------------------------\n" +
            "\n" +
            "open professorMat;\n" +
            "read_loop: loop\n" +
            "\tfetch professorMat into aux;\n" +
            "\tIF done THEN\n" +
            "\t\tLEAVE read_loop;\n" +
            "    END IF;\n" +
            "    IF aux = mat then\n" +
            "\t\tset achou=false;\n" +
            "        LEAVE read_loop;\n" +
            "\tEND IF;\n" +
            "END loop;\n" +
            "close professorMat;\n" +
            "\n" +
            "IF achou=true then\n" +
            "\tset str='(Não pode lecionar esta matéria) ';\n" +
            "END IF;\n" +
            "\n" +
            "SELECT count(*) into professorHour from (\n" +
            "  (SELECT DISTINCT dia,inicio FROM Disponivel d where not exists(SELECT DISTINCT dia,inicio FROM Ministrar m, Turma t, Marcado a where matriculaP=m.matricula_professor and m.cod_turma=t.cod_turma and t.cod_turma=a.cod_turma))\n" +
            "  UNION ALL \n" +
            "  (SELECT DISTINCT dia,inicio FROM Marcado m where codigoT=m.cod_turma)\n" +
            ") AS t1 GROUP BY dia,inicio HAVING count(*) >= 2;\n" +
            "SELECT count(*) into turmaHour from Marcado where codigoT=cod_turma;\n" +
            "\n" +
            "IF professorHour!=turmaHour then\n" +
            "\tset achou2=true;\n" +
            "    set str2='(Não pode lecionar duas matérias no mesmo horário) ';\n" +
            "END IF;\n" +
            "\n" +
            "\n" +
            "Select distinct count(*) into professorFilial from Ministrar x, Marcado r, Turma l where x.matricula_professor=matriculaP and x.cod_turma=l.cod_turma and l.cod_turma=r.cod_turma and exists(Select distinct t.cod_filial, a.dia from Marcado a, Turma t where codigoT=t.cod_turma and t.cod_turma=a.cod_turma and r.dia=a.dia and l.cod_filial!=t.cod_filial);\n" +
            "IF professorFilial>0 then\n" +
            "\tset achou=true;\n" +
            "    set str3='(Não pode ministrar duas turmas no mesmo dia em filiais diferentes)';\n" +
            "END IF;\n" +
            "\n" +
            "IF achou=false and achou2=false and achou3=false then\n" +
            "\tINSERT INTO Ministrar (cod_turma, matricula_professor) VALUES (codigoT,matriculaP);\n" +
            "\tset result='O Professor foi cadastrado na turma com sucesso!!';\n" +
            "ELSE\n" +
            "\tset result=concat('O Professor não pode ser cadasdtrado nessa turma pois: ',str,str2,str3);\n" +
            "END IF;\n" +
                    "END ";
    final public static String procedureInsereInscrito =
            "CREATE PROCEDURE insereInscrito(IN codigoT numeric, IN matriculaA numeric, OUT result text)\n" +
            "BEGIN\n" +
            "DECLARE done INT DEFAULT FALSE;\n" +
            "declare alunoTurma INT(4);\n" +
            "declare alunoFilial INT(4);\n" +
            "declare materiaCod INT(10);\n" +
            "declare turmaCod INT(10);\n" +
            "declare str text default '';\n" +
            "declare str2 text default '';\n" +
            "declare str3 text default '';\n" +
            "declare achou bool default false;\n" +
            "declare achou2 bool default false;\n" +
            "declare achou3 bool default false;\n" +
            "declare aux INT(10);\n" +
            "declare aux2 INT(10);\n" +
            "declare alunoMat CURSOR FOR select t.cod_turma,t.cod_materia from Inscrito i, Turma t where i.matricula_aluno=matriculaA and i.cod_turma=t.cod_turma;\n" +
            "DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;\n" +
            "\n" +
            "SELECT distinct count(*) into alunoTurma from (\n" +
            "  (Select distinct m.dia, m.inicio from Marcado m, Turma t, Inscrito i where matriculaA=i.matricula_aluno and i.cod_turma=t.cod_turma and t.cod_turma=m.cod_turma)\n" +
            "  UNION ALL \n" +
            "  (Select distinct m.dia, m.inicio from Marcado m, Turma t where codigoT=t.cod_turma and t.cod_turma=m.cod_turma)\n" +
            ") AS t1 group by dia, inicio Having count(*) >= 2;\n" +
            "\n" +
            "Select distinct count(*) into alunoFilial from Inscrito x, Marcado r, Turma l where x.matricula_aluno=matriculaA and x.cod_turma=l.cod_turma and l.cod_turma=r.cod_turma and \n" +
            "\texists(Select distinct t.cod_filial, a.dia, a.inicio from Marcado a, Turma t \n" +
            "\twhere codigoT=t.cod_turma and t.cod_turma=a.cod_turma and r.dia=a.dia and l.cod_filial!=t.cod_filial and (r.inicio+1=a.inicio or r.inicio-1=a.inicio));\n" +
            "\n" +
            "Select cod_turma,cod_materia into turmaCod,materiaCod from Turma where cod_turma=codigoT;\n" +
            "open alunoMat;\n" +
            "read_loop: loop\n" +
            "\tfetch alunoMat into aux,aux2;\n" +
            "    IF done THEN\n" +
            "\t\tLEAVE read_loop;\n" +
            "    END IF;\n" +
            "    IF aux2=materiaCod and aux!=turmaCod then\n" +
            "\t\tset achou=true;\n" +
            "\tEND IF;\n" +
            "END LOOP;\n" +
            "close alunoMat;\n" +
            "\n" +
            "IF achou=true then\n" +
            "\tset str='(Este aluno não pode assistir duas aulas diferentes no mesmo horário) ';\n" +
            "END IF;\n" +
            "IF alunoTurma>0 then\n" +
            "\tset achou2=true;\n" +
            "    set str2='(Este aluno não pode escolher matérias seguidas em filiais diferentes) ';\n" +
            "END IF;\n" +
            "\n" +
            "IF alunoFilial>0 then\n" +
            "\tset achou3=true;\n" +
            "    set str3='(Este aluno não pode assistir aulas em turmas diferentes da mesma matéria)';\n" +
            "END IF;\n" +
            "\n" +
            "IF achou=false and achou2=false and achou3=false then\n" +
            "\tset result='Este aluno foi inscrito com sucesso na turma!!';\n" +
            "    INSERT INTO Inscrito (matricula_aluno,cod_turma) VALUES (matriculaA,codigoT);\n" +
            "ELSE\n" +
            "\tset result=concat('Este aluno não pôde ser inscrito pois: ',str3,str,str2);\n" +
            "END IF;\n" +
                    "END";
    final public static String procedureInsereTurma =
            "CREATE TRIGGER tg_insere_turma\n" +
            "BEFORE INSERT ON Turma\n" +
            "FOR EACH ROW\n" +
            "BEGIN\n" +
            "\tDECLARE numAlunos INT(4);\n" +
            "    DECLARE numSala INT(10);\n" +
            "    DECLARE codigoFilial INT(10);\n" +
            "    DECLARE numAlunosTurma INT(10);\n" +
            "    SET numSala = NEW.num_sala;\n" +
            "    SET codigoFilial = NEW.cod_filial;\n" +
            "    SET numAlunosTurma = NEW.numMaxAlunos;\n" +
            "\tif numSala is null and codigoFilial is null then\n" +
            "\t\tif numAlunosTurma is null then\n" +
            "\t\t\tset NEW.numMaxAlunos = 50;\n" +
            "\t\tend if;\n" +
            "\telse\n" +
            "\t\tSET numAlunos = (SELECT numMaxAlunos From Sala Where num_sala = numSala and cod_filial = codigoFilial);\n" +
            "\t\tif numAlunosTurma is null or numAlunos < numAlunosTurma then\n" +
            "\t\t\tset NEW.numMaxAlunos = numAlunos;\n" +
            "\t\tEND if;\n" +
            "    END if;\n" +
                    "END;\n";
    final public static String[] insertFilial = new String[]{"INSERT INTO Filial (cod_filial,nome,endereco) VALUES(1024170215,\"Thaten55\",\"Rua Ipê 1215\");\n",
            "INSERT INTO Filial (cod_filial,nome,endereco) VALUES(1043640476,\"Thercull\",\"Rua Júlio Constantino 1987\");\n",
            "INSERT INTO Filial (cod_filial,nome,endereco) VALUES(1003327259,\"Persent57\",\"Rua Martha Vaccari Bellagamba Orlandi 995\");\n",
            "INSERT INTO Filial (cod_filial,nome,endereco) VALUES(1056688821,\"Therans1946\",\"Rua João Leal Soares 1150\");\n",
            "INSERT INTO Filial (cod_filial,nome,endereco) VALUES(1034478820,\"Youthis\",\"Rua Maria Gregório do Nascimento 1837\");\n"};
    final public static String[] insertSala = new String[]{"INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1003327259,102,35);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1003327259,101,49);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1024170215,101,21);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1034478820,102,47);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1034478820,101,24);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1043640476,102,33);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1043640476,101,43);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1056688821,103,39);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1056688821,102,43);\n",
            "INSERT INTO Sala (cod_filial,num_sala,numMaxAlunos) VALUES(1056688821,101,40);"};
    final public static String[] insertAluno = new String[]{"INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000031637,\"Bianca B Sousa\",48.23,\"Rua Sebastião Barbosa 483\",\"Manhã\",1034.08);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000034628,\"Fábio C Carvalho\",32.5,\"Rua Eurides de Oliveira 378\",\"Manhã\",7255.54);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000095044,\"Gabrielly F Azevedo\",17.79,\"Rua O 881\",\"Noite\",9582.21);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000117473,\"Giovana C Fernandes\",40.67,\"Rua Vitantônio D'Abril 1742\",\"Noite\",3695.71);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000125585,\"Gabriela S Ribeiro\",10.53,\"Rua Doutor Jaguaribe 784\",\"Tarde\",4191.96);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000141713,\"Felipe C Castro\",19.47,\"Rua RC 13 833\",\"Tarde\",2874.44);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000159682,\"Eduardo G Santos\",18.07,\"Rua Ester Fernandes Morgado 1051\",\"Noite\",3688.77);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000229689,\"Sarah R Barbosa\",27.56,\"Rua João Domingues Oliveira 1744\",\"Tarde\",8547.07);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000233963,\"Larissa P Almeida\",46.35,\"Rua Caetano Peterle 1222\",\"Manhã\",5932.31);\n",
            "INSERT INTO Aluno (matricula_aluno,nome,desconto,endereco,turno,mensalidadeBasica) VALUES(1000253299,\"Amanda C Cavalcanti\",40.9,\"Rua Waldemar Simões 1280\",\"Noite\",5457.65);\n"};
    final public static String[] insertVinculado = new String[]{"INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1043640476,1000031637);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1056688821,1000034628);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1043640476,1000095044);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1056688821,1000117473);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1003327259,1000125585);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1003327259,1000141713);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1024170215,1000159682);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1034478820,1000229689);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1003327259,1000233963);\n",
            "INSERT INTO Vinculado (cod_filial,matricula_aluno) VALUES(1043640476,1000253299);\n"};
    final public static String insertMateria[] = new String[]{"INSERT INTO Materia (cod_materia,horasPorSemana,mensalidade,nome) VALUES(1006604244,6,105.34,\"Matemática I\");\n",
            "INSERT INTO Materia (cod_materia,horasPorSemana,mensalidade,nome) VALUES(1066069257,4,153.01,\"Português\");\n",
            "INSERT INTO Materia (cod_materia,horasPorSemana,mensalidade,nome) VALUES(1048636040,5,173.16,\"Matemática II\");\n",
            "INSERT INTO Materia (cod_materia,horasPorSemana,mensalidade,nome) VALUES(1053625735,5,93.19,\"Inglês\");\n",
            "INSERT INTO Materia (cod_materia,horasPorSemana,mensalidade,nome) VALUES(1024696431,4,199.99,\"História\");\n"};
    final public static String insertHorario[] = new String[]{"INSERT INTO Horario (dia,inicio) VALUES (1,8);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,9);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,10);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,11);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,13);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,14);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,15);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,16);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,18);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,19);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,20);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (1,21);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,8);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,9);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,10);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,11);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,13);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,14);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,15);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,16);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,18);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,19);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,20);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (2,21);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,8);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,9);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,10);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,11);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,13);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,14);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,15);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,16);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,18);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,19);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,20);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (3,21);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,8);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,9);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,10);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,11);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,13);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,14);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,15);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,16);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,18);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,19);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,20);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (4,21);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,8);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,9);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,10);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,11);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,13);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,14);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,15);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,16);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,18);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,19);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,20);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (5,21);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,8);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,9);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,10);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,11);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,13);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,14);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,15);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,16);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,18);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,19);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,20);\n",
            "INSERT INTO Horario (dia,inicio) VALUES (6,21);\n"};
    final public static String[] insertProfessor = new String[]{"INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1001955977,\"Nicolas S Pereira\",\"Rua Monte Castelo 424\",875.69);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1002709499,\"Fernanda G Fernandes\",\"Avenida Segunda Avenida Bloco 1600 788\",703.13);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1002947356,\"Livia F Rocha\",\"Rua Maria Solange Lira Rosa 946\",609.76);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1003011848,\"Larissa B Castro\",\"Rua João Leal Soares 1150\",378.42);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1003081350,\"Rodrigo R Barbosa\",\"Rua B 498\",601.59);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1003555133,\"Julia O Araujo\",\"Rua Mário Sabóia Bandeira Mello 69\",357.3);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1003784087,\"Matilde A Carvalho\",\"Rua Ercílio Slaviero 1892\",920.08);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1004150036,\"Giovana P Cardoso\",\"Rua Nossa Senhora das Graças 965\",571.18);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1004325970,\"Daniel S Oliveira\",\"Rua Santo Antônio do Amparo 71\",820.06);\n",
            "INSERT INTO Professor (matricula_professor,nome,endereco,horaAula) VALUES(1013548819,\"Murilo C Castro\",\"Rua Rui Barbosa 1361\",702.49);\n"};
    final public static String[] insertDisponivel = new String[]{"INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1001955977,2,13);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1001955977,2,14);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1001955977,5,13);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1001955977,5,14);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1003011848,1,8);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1003011848,1,9);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1003011848,1,10);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1003011848,3,8);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1003011848,3,9);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1003011848,3,10);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,8);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,9);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,10);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,11);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,5,8);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,13);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,14);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,15);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES(1002709499,1,16);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1002947356,1,18);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1002947356,1,19);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1002947356,1,20);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1002947356,3,18);\n",
            "INSERT INTO Disponivel (matricula_professor,dia,inicio) VALUES (1002947356,3,19);\n"};
    final public static String[] insertLeciona = new String[]{"INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1001955977,1066069257);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1001955977,1006604244);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1002709499,1053625735);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1003784087,1048636040);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1002709499,1024696431);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1002947356,1006604244);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1002947356,1053625735);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1003011848,1066069257);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1013548819,1048636040);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1003555133,1024696431);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1002947356,1066069257);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1004325970,1048636040);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1002947356,1024696431);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1002947356,1048636040);\n",
            "INSERT INTO Leciona (matricula_professor,cod_materia) VALUES(1003011848,1006604244);\n"};
    final public static String[] insertTurma = new String[]{"INSERT INTO Turma (cod_turma,num_sala,cod_filial,cod_materia,numMaxAlunos,ano) VALUES (1057364200,102,1003327259,1006604244,30,2015);\n",
            "INSERT INTO Turma (cod_turma,num_sala,cod_filial,cod_materia,numMaxAlunos,ano) VALUES (1069156297,102,1003327259,1066069257,30,2015);\n",
            "INSERT INTO Turma (cod_turma,num_sala,cod_filial,cod_materia,numMaxAlunos,ano) VALUES (1047362835,101,1003327259,1048636040,4,2015);\n",
            "INSERT INTO Turma (cod_turma,num_sala,cod_filial,cod_materia,numMaxAlunos,ano) VALUES (1039622922,101,1056688821,1053625735 ,30,2015);\n"};
    final public static String[] insertInscrito = new String[]{"INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1057364200,1000031637);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1057364200,1000034628);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1057364200,1000095044);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1057364200,1000117473);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1057364200,1000125585);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1057364200,1000141713);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1069156297,1000031637);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1069156297,1000117473);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1069156297,1000159682);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1069156297,1000229689);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1047362835,1000031637);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1047362835,1000034628);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1047362835,1000095044);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1047362835,1000233963);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1047362835,1000253299);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1039622922,1000034628);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1039622922,1000117473);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1039622922,1000233963);\n",
            "INSERT INTO Inscrito (cod_turma,matricula_aluno) VALUES(1039622922,1000253299);\n"};
    final public static String[] insertMinistrar = new String[]{"INSERT INTO Ministrar (cod_turma,matricula_professor) VALUES(1069156297,1001955977);\n",
            "INSERT INTO Ministrar (cod_turma,matricula_professor) VALUES(1057364200,1003011848);\n",
            "INSERT INTO Ministrar (cod_turma,matricula_professor) VALUES(1039622922,1002709499);\n",
            "INSERT INTO Ministrar (cod_turma,matricula_professor) VALUES(1047362835,1002947356);"};
    final public static String[] insertMarcado = new String[]{"INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1069156297,2,13);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1069156297,2,14);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1069156297,5,13);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1069156297,5,14);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1057364200,1,8);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1057364200,1,9);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1057364200,1,10);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1057364200,3,8);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1057364200,3,9);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1057364200,3,10);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES(1039622922,1,8);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES(1039622922,1,9);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES(1039622922,1,10);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES(1039622922,1,11);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES(1039622922,5,8);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1047362835,1,18);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1047362835,1,19);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1047362835,1,20);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1047362835,3,18);\n",
            "INSERT INTO Marcado (cod_turma,dia,inicio) VALUES (1047362835,3,19);\n"};
    final public static String viewAlunoFilial = "CREATE VIEW vw_aluno_filial AS\n" +
            "    (SELECT \n" +
            "        Aluno.matricula_aluno AS Matricula_do_Aluno,\n" +
            "        Aluno.nome AS Nome_do_Aluno,\n" +
            "        Filial.cod_filial AS Codigo_da_Filial,\n" +
            "        Filial.nome AS Filial\n" +
            "    FROM\n" +
            "        ((Aluno\n" +
            "        JOIN Filial)\n" +
            "        JOIN Vinculado)\n" +
            "    WHERE\n" +
            "        ((Aluno.matricula_aluno = Vinculado.matricula_aluno)\n" +
            "            AND (Filial.cod_filial = Vinculado.cod_filial))\n" +
            "\tORDER BY Nome_do_Aluno)";
    final public static String viewAlunoMateria = "CREATE VIEW vw_aluno_materia AS\n" +
            "    (SELECT \n" +
            "        Aluno.matricula_aluno AS Matricula_do_Aluno,\n" +
            "        Aluno.nome AS Nome,\n" +
            "        MATERIASALUNO(Aluno.matricula_aluno) AS Materias\n" +
            "    FROM\n" +
            "        Aluno\n" +
            "\tORDER BY Nome)";
    final public static String viewAlunoMensalidade = "CREATE VIEW vw_aluno_mensalidade AS\n" +
            "    (SELECT \n" +
            "        Aluno.matricula_aluno AS Matricula_do_Aluno,\n" +
            "        Aluno.nome AS Nome_do_Aluno,\n" +
            "        CALCULAMENSALIDADECOMJUROS(Aluno.matricula_aluno) AS Mensalidade_Total_Com_Desconto\n" +
            "    FROM\n" +
            "        `Aluno`\n" +
            "\tORDER BY Nome_do_Aluno)";
    final public static String viewLeciona = "CREATE VIEW vw_leciona AS\n" +
            "    SELECT \n" +
            "        Leciona.matricula_professor AS matricula_professor,\n" +
            "        Leciona.cod_materia AS cod_materia\n" +
            "    FROM\n" +
            "        Leciona\n" +
            "    WHERE\n" +
            "        (Leciona.cod_materia = 1006604244) WITH CASCADED CHECK OPTION";
    final public static String viewProfessorInfo = "CREATE VIEW vw_professor_info AS\n" +
            "    (SELECT distinct\n" +
            "        Professor.matricula_professor AS Matricula_do_Professor,\n" +
            "        Professor.nome AS Professor,\n" +
            "        Materia.cod_materia AS Codigo_da_Materia,\n" +
            "        Materia.nome AS Materia,\n" +
            "        Filial.cod_filial AS Codigo_da_Filial,\n" +
            "        Filial.nome AS Filial,\n" +
            "        Filial.endereco AS Endereço\n" +
            "    FROM\n" +
            "        (((((Professor\n" +
            "        JOIN Turma)\n" +
            "        JOIN Materia)\n" +
            "        JOIN Ministrar)\n" +
            "        JOIN Sala)\n" +
            "        JOIN Filial)\n" +
            "    WHERE\n" +
            "        ((Professor.matricula_professor = Ministrar.matricula_professor)\n" +
            "            AND (Turma.cod_turma = Ministrar.cod_turma)\n" +
            "            AND (Turma.cod_materia = Materia.cod_materia)\n" +
            "            AND (Turma.num_sala = Sala.num_sala)\n" +
            "            AND (Sala.cod_filial = Filial.cod_filial))\n" +
            "\tORDER BY Professor)";
    final public static String viewProfessorSalario = "CREATE VIEW vw_professor_salario AS\n" +
            "    (SELECT \n" +
            "        Professor.matricula_professor AS Matricula,\n" +
            "        Professor.nome AS Professor,\n" +
            "        Professor.horaAula AS Hora_Aula,\n" +
            "        CALCULASALARIO(Professor.matricula_professor) AS Salario\n" +
            "    FROM\n" +
            "        Professor\n" +
            "\tORDER BY Professor)";

}

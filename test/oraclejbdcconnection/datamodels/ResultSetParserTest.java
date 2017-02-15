package oraclejbdcconnection.datamodels;

import oraclejbdcconnection.sql.connection.JBDCSQLConnection;
import oraclejbdcconnection.sql.sqlreader.SqlRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by who cares on 2/14/2017.
 */
class ResultSetParserTest {

    private static JBDCSQLConnection oraclecon;

    @BeforeAll
    public static void setup() {
        oraclecon = new JBDCSQLConnection();
        oraclecon.setPort(1521);
        oraclecon.setSid("orcl");
        boolean pass = oraclecon.createOracleConnection("localhost", "test1", "pass1");

        assertTrue(pass);
    }

    @Test
    public void run_table_operations_file() {
        SqlRunner sqlr = SqlRunner.getInstance();
        sqlr.setConnection(oraclecon.getConnection());

        sqlr.run("resources/SQL_Scripts_test/create_table_test.sql");
        sqlr.run("resources/SQL_Scripts_test/insert_test.sql");

        ArrayList<LinkedList<String>> results = ResultSetParser.parseResultSetIntoArray(sqlr.query("resources/SQL_Scripts_test/select_test.sql"), "FirstName");

        if (results == null) {
            fail("Empty query");
        } else {
            for (int i = 0; i < results.get(0).size(); i++) {
                String values = results.get(0).get(i);
                if (values == null) {
                } else {
                    System.out.println(values);
                }
            }
        }

        sqlr.run("resources/SQL_Scripts_test/drop_table_test.sql");
    }


}

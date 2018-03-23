package database.datamodels;

import database.sql.connection.JBDCConnection;
import database.sql.queryreader.SqlRunner;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static junit.framework.TestCase.assertTrue;

class ResultSetParserTest {

    private static JBDCConnection oraclecon;

    @Before
    public static void setup() {
        oraclecon = new JBDCConnection();
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

        try {
            RetrievedSet.parseResultSet("User", sqlr.query("resources/SQL_Scripts_test/select_test.sql"), "FirstName");

            if (RetrievedSet.getObject("FirstName") == null) {
                fail("Empty query");
            }
        } catch (DataModelException e) {
            e.printStackTrace();
        }


        sqlr.run("resources/SQL_Scripts_test/drop_table_test.sql");
    }

    private void fail(String empty_query) {
    }


}

package oraclejbdcconnection.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import oraclejbdcconnection.sql.sqlreader.SqlRunner;

public class SqlScriptReaderTest {

    @Test
    public void test() {
        SqlRunner sqlreader = SqlRunner.getInstance();
        sqlreader.run("SQL_Scripts/projectDefs.sql");
        sqlreader.run("SQL_Scripts/dataInserts.sql");

        ResultSet testResult = sqlreader.query("SQL_Scripts/testQueries.sql");

        try {
            while (testResult.next()) {
                System.out.println(testResult.getString("userid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

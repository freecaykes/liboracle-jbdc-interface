package oraclejbdcconnection.test;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import oraclejbdcconnection.datamodels.ResultSetParser;
import oraclejbdcconnection.sql.sqlreader.SqlRunner;

public class ResultSetParserTest {

    @Test
    public void test() {
        boolean sfd = result();
    }

    public boolean result() {
        String userID = "Raphael";
        SqlRunner sqlreader = SqlRunner.getInstance();
        ArrayList<LinkedList<String>> users = ResultSetParser.parseResultSetIntoArray(sqlreader.query("SQL_Scripts/checkAdmin.sql"), "name");
        if (users == null) {
            return false;
        } else {
            for (int i = 0; i < users.get(0).size(); i++) {
                String values = users.get(0).get(i);
                if (values == null) {
                    return false;
                } else if (values.equals(userID)) {
                    return true;
                }
            }
        }
        return false;
    }
}

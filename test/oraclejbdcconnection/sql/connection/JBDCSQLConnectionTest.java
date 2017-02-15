package oraclejbdcconnection.sql.connection;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by who cares on 2/12/2017.
 */
class JBDCSQLConnectionTest {

    @Test
    public void test_no_exception_default_port_sid() {
        JBDCSQLConnection oraclecon = new JBDCSQLConnection();
        oraclecon.setPort(1521);
        oraclecon.setSid("orcl");
        boolean pass = oraclecon.createOracleConnection("localhost", "test1", "pass1");

        assertTrue(pass);
        if (pass) {
            oraclecon.closeConnection();
        }
    }

    @Test
    public void test_false_password() {
        JBDCSQLConnection oraclecon = new JBDCSQLConnection();

        boolean pass = oraclecon.createOracleConnection("localhost", "test1", "pass2");
        assertFalse(pass);

        if (pass) {
            oraclecon.closeConnection();
        }

    }
}
package database.sql.connection;

import org.junit.Test;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by who cares on 2/12/2017.
 */
class JBDCSQLConnectionTest {

    @Test
    public void test_no_exception_default_port_sid() {
        JBDCConnection oraclecon = new JBDCConnection();
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
        JBDCConnection oraclecon = new JBDCConnection();

        boolean pass = oraclecon.createOracleConnection("localhost", "test1", "pass2");
        assertFalse(pass);

        if (pass) {
            oraclecon.closeConnection();
        }

    }
}
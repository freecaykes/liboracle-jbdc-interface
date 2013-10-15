package pizzawatch.sql.connection.oracleconnectiontests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pizzawatch.sql.connection.JBDCSQLConnection;

public class OracleConnectionTest {
	
	
	@Test
	public void test() {
		JBDCSQLConnection sqlcon = new JBDCSQLConnection();
		sqlcon.setOracleConnection();
	}
	
}

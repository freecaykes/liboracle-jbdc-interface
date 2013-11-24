package pizzawatch.sql.functions.test;

import org.junit.Test;

import pizzawatch.sql.connection.JBDCSQLConnection;

public class OracleConnectionTest {
	
	@Test
	public void test() {
		JBDCSQLConnection sqlcon = new JBDCSQLConnection();
		sqlcon.setOracleConnection();
	}
	
}

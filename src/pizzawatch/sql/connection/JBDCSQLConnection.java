package pizzawatch.sql.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JBDCSQLConnection {

	private final static String USER_NAME = "ora_i2c8";
	private final static String USER_PASSWORD = "a30758114";
	private final static String CONNECTION_URL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug";
	
	public void connectOracle()
	{
		int retry = 5;
		
		System.out.println("--------------------- Starting Oracle connection ---------------------");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			/* will continue on even if class is not found*/
		}
		System.out.println("Oracle driver is found");
		
		makeConnection();
	}
	
	private void makeConnection()
	{
		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1521:ug", "ora_i2c8", "a30758114");
			System.out.println("Successfully Connected");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JBDCSQLConnection sqlcon = new JBDCSQLConnection();
		sqlcon.connectOracle();
	}

}

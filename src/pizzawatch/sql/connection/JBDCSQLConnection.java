package pizzawatch.sql.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JBDCSQLConnection {

	private final static String USER_NAME = "i2c8";
	private final static String USER_PASSWORD = "Iam91121";
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
			return;
		}
		System.out.println("Oracle driver is found");
		
		makeConnection();
	}
	
	private void makeConnection()
	{
		try {
			Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

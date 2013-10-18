package pizzawatch.sql.sqlreader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import pizzawatch.sql.connection.JBDCSQLConnection;

public class SqlScriptReader {
	
	private static final String DELIMITER = ";";
	
	private Connection connection;
	
	private void setConnection()
	{
		JBDCSQLConnection sqlConnector = new JBDCSQLConnection();
		sqlConnector.setOracleConnection();
		this.connection = sqlConnector.getConnection();
	}
	
	/**
	 * reads in an .sql file and runs the query/script in the SQL file  
	 * 
	 * @param fileName
	 */
	public void runScript(String source) throws SQLException
	{
		Statement currStatement = null;
		Scanner scanner = new Scanner(source).useDelimiter(DELIMITER);
		setConnection();
		while(scanner.hasNext())
		{
			String query = scanner.next() + DELIMITER;
			try
			{
				if(connection != null)
				{
					currStatement = connection.createStatement();
					currStatement.execute(query);
				}	
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				if(currStatement != null)
				{
					currStatement.close();
				}
			}
		}
	}
	
}

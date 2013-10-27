package pizzawatch.sql.sqlreader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import pizzawatch.sql.connection.JBDCSQLConnection;

public class SqlScriptReader {
	
	private static final String DELIMITER = ";";
	
	private Connection connection;
	
	/**
	 * reads in an .sql file and runs the query/script in the SQL file  
	 * 
	 * @param fileName
	 */
	private PreparedStatement runScript(String source)
	{
		PreparedStatement currStatement = null;
		Scanner scanner = new Scanner(source).useDelimiter(DELIMITER);
		if(connection == null)
		{
			setConnection();
		}
		while(scanner.hasNext())
		{
			String query = scanner.next() + DELIMITER;
			try
			{
				if(connection != null)
				{
					currStatement = connection.prepareStatement(query);
					currStatement.executeUpdate();
					
					if(currStatement != null)
						currStatement.close();
				}	
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		return currStatement;
	}
	
	public void insertUpdateCreateDelete(String source) throws SQLException
	{
		runScript(source);
	}
	
	public ResultSet selectQueryFromTable(String source) throws SQLException
	{
		return runScript(source).executeQuery();
	}
	
	private void setConnection()
	{
		JBDCSQLConnection sqlConnector = new JBDCSQLConnection();
		sqlConnector.setOracleConnection();
		this.connection = sqlConnector.getConnection();
	}
}

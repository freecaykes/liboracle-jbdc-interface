package pizzawatch.sql.sqlreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import pizzawatch.sql.connection.JBDCSQLConnection;

public class SqlScriptReader {
	
	private static final String DELIMITER = ";";
	
	private Connection connection;
	
	/**
	 * reads in an .sql file and runs the query/script in the SQL file  
	 * 
	 * @param fileName
	 * @throws SQLException 
	 */
	private PreparedStatement runScript(String source) throws SQLException
	{
		try{
			PreparedStatement currStatement = null;
			Scanner scanner = new Scanner(new File(source)).useDelimiter(DELIMITER);
			if(connection == null)
			{
				setConnection();
			}
			while(scanner.hasNext())
			{
				String query = scanner.next() + DELIMITER;
				
				if(connection != null && query.contains("E"))
				{
					System.out.print("query: \n" + query + "\n\n");
					query = query.substring(0,query.length() - 1);
					currStatement = connection.prepareStatement(query);
					if(!query.contains("SELECT"))
						currStatement.execute();
				}	
			}
			
			return currStatement;
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void insertUpdateCreateDelete(String source)
	{
		try{
			PreparedStatement currStatement = runScript(source);
			currStatement.executeUpdate();
			if(currStatement != null)
				currStatement.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public ResultSet query(String source) 
	{
		ResultSet results = null;
		try {
			PreparedStatement currStatement = runScript(source);
			results = currStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	private void setConnection()
	{
		JBDCSQLConnection sqlConnector = new JBDCSQLConnection();
		sqlConnector.setOracleConnection();
		this.connection = sqlConnector.getConnection();
	}
}

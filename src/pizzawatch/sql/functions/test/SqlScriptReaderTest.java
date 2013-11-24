package pizzawatch.sql.functions.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import pizzawatch.sql.sqlreader.SqlScriptReader;

public class SqlScriptReaderTest {

	@Test
	public void test() {
		SqlScriptReader sqlreader = new SqlScriptReader();
		//sqlreader.insertUpdateCreateDelete("SQL_Scripts/projectDefs.sql");
		//sqlreader.insertUpdateCreateDelete("SQL_Scripts/updateTables-1.sql");
		
		ResultSet testResult = sqlreader.query("SQL_Scripts/checkAdmin.sql");
		
		try {
			while(testResult.next())
			{
				System.out.println(testResult.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

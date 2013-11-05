package pizzawatch.sql.connection.oracleconnectiontests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import pizzawatch.sql.sqlreader.SqlScriptReader;

public class SqlScriptReaderTest {

	@Test
	public void test() {
		SqlScriptReader sqlreader = new SqlScriptReader();
		sqlreader.insertUpdateCreateDelete("SQL_scripts/projectDefs.sql");
		sqlreader.insertUpdateCreateDelete("SQL_scripts/dataInserts.sql");
		
		ResultSet testResult = sqlreader.query("SQL_scripts/testQueries.sql");
		
		try {
			while(testResult.next())
			{
				System.out.println(testResult.getString("userID") + "  " + testResult.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

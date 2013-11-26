package pizzawatch.sql.functions.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import pizzawatch.sql.sqlreader.SqlScriptReader;

public class SqlScriptReaderTest {

	@Test
	public void test() {
		SqlScriptReader sqlreader = SqlScriptReader.getInstance();
		sqlreader.insertUpdateCreateDelete("SQL_Scripts/projectDefs.sql");
		sqlreader.insertUpdateCreateDelete("SQL_Scripts/dataInserts.sql");

		ResultSet testResult = sqlreader.query("SQL_Scripts/testQueries.sql");

		try {
			while(testResult.next())
			{
				System.out.println(testResult.getString("userid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

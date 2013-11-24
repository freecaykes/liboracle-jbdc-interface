package pizzawatch.sql.functions.test;

import java.util.ArrayList;
import java.util.LinkedList;

import pizzawatch.sql.sqlreader.ResultSetParser;
import pizzawatch.sql.sqlreader.SqlScriptReader;
import org.junit.Test;

public class ResultSetParserTest {

	@Test
	public void test()
	{
		boolean sfd = result();
	}
	
	public boolean result()
	{
		String userID = "Raphael";
		SqlScriptReader sqlreader = new SqlScriptReader();
		ArrayList<LinkedList<String>> users = ResultSetParser.parseResultSetIntoArray(sqlreader.query("SQL_Scripts/checkAdmin.sql"), "name");
		if(users.equals(null)){return false;}
		else
		{
			for(int i=0;i<users.get(0).size();i++)
			{
				String values = users.get(0).get(i);
				if(values.equals(null))
				{
					return false;
				}else if(values.equals(userID))
				{
					return true;
				}
			}
		}
		return false;
	}
}

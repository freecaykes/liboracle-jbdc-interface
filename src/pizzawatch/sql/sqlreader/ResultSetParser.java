package pizzawatch.sql.sqlreader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetParser {
	
	public static String[][] parseResultSetIntoArray(ResultSet rs, String... attributes)
	{
		int counter = 0;
		String[][] parsedResults = new String[attributes.length -1][];
		try {
			while(rs.next())
			{
				for(int i=0; i<attributes.length; i++)
				{
					parsedResults[i][counter] = rs.getString(attributes[i]);
				}
			}//
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return parsedResults;
	}
}


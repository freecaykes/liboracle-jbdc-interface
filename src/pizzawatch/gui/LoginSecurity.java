package pizzawatch.gui;

import java.util.Hashtable;

import pizzawatch.sql.sqlreader.ResultSetParser;
import pizzawatch.sql.sqlreader.SqlScriptReader;

public class LoginSecurity {
	private Hashtable<String, String> usersPasswords = new Hashtable<String, String>();
	
	/**
	 * @param userID
	 * @param userPass
	 * @return password of String userID in hashTable if userPass == the corresponding user's
	 * password in Tables 
	 * -1 otherwise
	 */
	public String loginUser(String userID, String userPass)
	{
		if(usersPasswords.get(userID) == userPass)
		{
			return usersPasswords.get(userID);
		}
		
		return "-1";
	}
	
	public boolean checkAdmin(String userID)
	{
		SqlScriptReader sqlreader = new SqlScriptReader();
		String[][] users = ResultSetParser.parseResultSetIntoArray(sqlreader.query("checkAdmin.sql"), "userID");
		for(int i=0;i<users[0].length;i++)
		{
			if(users[0][i]== userID)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void hashPassword(String userID, String userPass)
	{
		if(!usersPasswords.contains(userID))
		{
			usersPasswords.put(userID, userPass);
		}
	}
	
}

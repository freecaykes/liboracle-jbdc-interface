package pizzawatch.gui.functions;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import pizzawatch.sql.sqlreader.ResultSetParser;
import pizzawatch.sql.sqlreader.SqlScriptReader;

public class User {
	@SuppressWarnings("serial")
	private static final Map<String, String> usersPasswords = new HashMap<>(16);
	private String name;
	private SqlScriptReader sqlreader = new SqlScriptReader();
	public boolean admin = false;
	
	public User(String name)
	{
		this.name = name;
	}
	
	public String getUserID()
	{
		ArrayList<LinkedList<String>> users = ResultSetParser.parseResultSetIntoArray(sqlreader.query("SELECT userID FROM Users WHERE name = '"+name+"'"), "userID");
		return users.get(0).get(0);
	}
	
	public void initializePasswords()
	{
            usersPasswords.put("Leonardo", "5ca888f39d61adfe533b0c08bd9f884ee6ff83d69c1221491ecad366dc56b646"); //watern4tur3
            usersPasswords.put("Raphael", "b1f51a511f1da0cd348b8f8598db32e61cb963e5fc69e2b41485bf99590ed75a"); //red
            usersPasswords.put("Michaelangelo", "c207b1b9e510364443db9423b36bc5f16df95de58a544a64b9d80b0feba78065"); //kawabanga
            usersPasswords.put("Donatello", "8e0a1b0ada42172886fd1297e25abf99f14396a9400acbd5f20da20289cff02f"); //purple
            usersPasswords.put("Master Splinter", "6b649d9c83a8e2e01b9b34f442af5a25797efe2187f9528da0c481cdf4a4e1e0"); //p3aceinm1nd
	}
	/**
	 * @param userID
	 * @param userPass
	 * @return password of String userID in hashTable if userPass == the corresponding user's
	 * password in Tables
	 * null otherwise
	 */
	public String loginUser(String userID, String userPass)
	{
        String hashOfGivenPassword = hashPassword(userPass);
        String real = usersPasswords.get(userID);
        if(real != null && hashOfGivenPassword != null && real.equalsIgnoreCase(hashOfGivenPassword))
        {
            return usersPasswords.get(userID);
        }

        return null;
	}

	/**
	 * check if user admin
	 * @param userID
	 * @return true when an admin user
	 */
	public boolean checkAdmin(String userID)
	{
		ArrayList<LinkedList<String>> users = ResultSetParser.parseResultSetIntoArray(sqlreader.query("SQL_Scripts/checkAdmin.sql"), "name");

		if(users == null){return false;}
		else
		{
			for(int i=0;i<users.get(0).size();i++)
			{
				String values = users.get(0).get(i);
				if(values == null)
				{
					return false;
				}else if(values.equals(userID))
				{
					admin = true;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * conver bytes to HEXbytes for hashing
	 * @param userID
	 * @param userPass
	 */

    //From http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
    private static String convertToHex(byte[] bytes)
    {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++)
        {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

        /**
         * Hash secure pass
         * @param userPass
         * @return The hash, or null if there is an error
         */
	public String hashPassword(String userPass)
	{
            try
            {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(userPass.getBytes("UTF-8"), 0, userPass.length());
                return convertToHex(md.digest());
            }
            catch(NoSuchAlgorithmException | UnsupportedEncodingException ex)
            {
                //TODO error out properly
                return null;
            }
	}
	
	//Please Complete this using division
	/**
	 * get all of 1 user's orders
	 * @return
	 */
	public ArrayList<LinkedList<String>> getOrders()
	{
		return ResultSetParser.parseResultSetIntoArray(sqlreader.query("   " + getUserID()), "oid;deliveryMethod;pizzaType;address");
	}
	
	/**
	 * an admin  can delete an order
	 */
	public void deleteOrders(String uid, String oid)
	{
		if(admin)
		{
			String del_query = "DELETE FROM PizzaOrder WHERE userID = " + "'" +uid +"'" +" AND oid = "+ "'" +oid +"'";
			sqlreader.insertUpdateCreateDelete(del_query);
		}
	}

}

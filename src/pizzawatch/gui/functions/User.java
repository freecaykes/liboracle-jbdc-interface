package pizzawatch.gui.functions;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pizzawatch.sql.sqlreader.ResultSetParser;
import pizzawatch.sql.sqlreader.SqlScriptReader;

@SuppressWarnings("serial")
public class User
{
    private static final Map<String, String> usersPasswords = new HashMap<>(16);
    private final String name;
    private static final SqlScriptReader sqlreader = new SqlScriptReader();
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
     * Compares a given password hash and the real password hash of the given user
     * @param userID
     * @param userPass
     * @return Password of String userID in hashTable if userPass == the corresponding user's
     * password in Tables, or null otherwise
     */
    public String compareUserPasswordHash(String userID, String userPass)
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
     * join query
     * @param userID
     * @return true when an admin user
     */
    public boolean checkAdmin(String userID)
    {
        ArrayList<LinkedList<String>> users = ResultSetParser.parseResultSetIntoArray(sqlreader.query("SQL_Scripts/checkAdmin.sql"), "name");

        if(users == null) {return false;}
        else
        {
            for(int i = 0; i < users.get(0).size(); i++)
            {
                String values = users.get(0).get(i);
                if(values == null)
                {
                    return false;
                }
                else if(values.equals(userID))
                {
                    admin = true;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Convert byte array to a (caps) hex string
     * Taken from http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
     * @param userID
     * @param userPass
     */
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
            return null;
        }
    }

    /**
     * Returns a TableModel containing the Past Orders for the given users
     * Assumes userIDs[0] is defined
     * Our Division Query
     * @param userIDs An array containing the user IDs
     * @return A TableModel for use by the PastOrdersFrame JList
     */
    public static TableModel getPastOrdersTableModel(int[] userIDs)
    {
        //TODO protect against non-admin multiuser queries
        final String ATTRIBUTES_STRING = "userID;oid;deliveryMethod;pizzaType;address"; //Keep in sync with TABLE_TITLES
        final String[] TABLE_TITLES = {"User ID", "Order ID", "Delivery Method", "Pizza Type", "Address"}; //Keep in sync with ATTRIBUTES_STRING
        DefaultTableModelNoEdit tableModel = new DefaultTableModelNoEdit();

        String queryString = "SELECT * FROM PizzaOrder po WHERE po.userID IN " +
                             "(SELECT u.userID FROM Users u WHERE u.userID = " + userIDs[0]; //Add the first user ID
        for(int x = 1; x < userIDs.length; x++)
        {
            queryString += " OR u.userID = " + userIDs[x]; //Add any remaining user IDs
        }
        queryString += ")";

        ArrayList<LinkedList<String>> attributesList = ResultSetParser.parseResultSetIntoArray(sqlreader.query(queryString), ATTRIBUTES_STRING);
        for(int x = 0; x < attributesList.size(); x++)
        {
            LinkedList<String> attributesDataList = attributesList.get(x);
            tableModel.addColumn(TABLE_TITLES[x], attributesDataList.toArray());
        }

        return tableModel;
    }

    /**
     * Deletes an order if the current user is an admin
     * Our delete query.
     * @param uid The user ID
     * @param oid The order ID
     */
    public void deleteOrders(String uid, String oid)
    {
        if(admin)
        {
            String del_query = "DELETE FROM PizzaOrder WHERE userID = " + "'" +uid +"'" +" AND oid = "+ "'" +oid +"'";
            sqlreader.insertUpdateCreateDelete(del_query);
        }
    }

    /**
     * our nested aggregate query
     * @return
     */
    public String punish()
    {
        if(admin)
        {
            String sum_query = "CREATE VIEW [User Total Sum] AS" +
                       "SELECT SUM(p.price) AS user_sum, u.userID" +
                       "FROM Users u, PizzaOrder po, Pizza p" +
                       "WHERE u.userID = po.userID AND po.pizzaType = p.PizzaType" +
                       "GROUP BY u.userID";
            String max_query = "SELECT MAX(user_sum), userID FROM [User Total Sum] GROUP BY userID";
            sqlreader.query(sum_query);
            ArrayList<LinkedList<String>> total_user_sum = ResultSetParser.parseResultSetIntoArray(sqlreader.query(max_query), "userID");
            return total_user_sum.get(0).get(0);
        }
        return null;
    }

    /**
     * our aggregate query
     * @return
     */
    public String getTotalSum()
    {
        String sum_query = "SELECT SUM(p.price)" +
                           "FROM Users u, PizzaOrder po, Pizza p" +
                           "WHERE u.userID = po.userID AND po.pizzaType = p.PizzaType AND u.userID = " + getUserID()+
                           "GROUP BY u.userID";
        ArrayList<LinkedList<String>> total_user_sum = ResultSetParser.parseResultSetIntoArray(sqlreader.query(sum_query), "price");
        return total_user_sum.get(0).get(0);
    }
}

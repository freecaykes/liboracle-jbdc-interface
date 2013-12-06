package pizzawatch.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JBDCSQLConnection
{
    private final static String USER_NAME = ""; /*ora_<cs_id>*/
    private final static String USER_PASSWORD = ""; /*a<Student Number> this like how we connected to SQL_Plus in lab*/
    private final static String CONNECTION_URL = ""; /*port 1521 has problems*/
    
    /**
     * treat connection as singleton 
     */
    private Connection connection;

    /**
     * Sets up the connection to the Oracle DB == connection.getInstance()
     * @return Whether the connection succeeded or not
     */
    public boolean setOracleConnection()
    {
        int retry = 5;

        //System.out.println("--------------------- Starting Oracle connection ---------------------");
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch(ClassNotFoundException e)
        {
            //e.printStackTrace();
            return false;
        }
        //System.out.println("Oracle driver is found");
        Connection con = makeConnection();
        while(con == null && retry > 0)
        {
            con = makeConnection();
            retry--;
        }

        if(con == null)
        {
            return false; //Connection has not been established after all retries - return failure
        }

        this.connection = con;

        return true;
    }

    public Connection getConnection()
    {
        return this.connection;
    }

    private Connection makeConnection()
    {
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASSWORD);
            //System.out.println("Successfully Connected");
            return con;
        } catch (SQLException e) {
            //System.out.println("Connection failed \n");
            //e.printStackTrace();
        }
        return null;
    }
}

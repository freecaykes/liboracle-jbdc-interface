package database.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JBDCConnection {
    private final static int MAX_RETRY = 5;

    private String dbConnectionUrl = ""; /*form jdbc:oracle:thin:@host_name:port_number:sid*/

    /*Default Oracle DB values*/
    private int port = 1521;  /*Oracle Default port*/
    private String sid = "orcl";

    /*treat connection as singleton*/
    private Connection connection;

    public void setPort(int port) {
        this.port = port;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    private String createOracleUrl(String hostName, int port, String sid) throws SQLException {
        if (sid.length() < 1 || sid == null) {
            sid = this.sid;
        }
        if (400 < port && port < 65535) {
            /*form jdbc:oracle:thin:@host_name:port_number:sid*/
            return new StringBuilder().append("jdbc:oracle:thin:@").append(hostName).append(":").append(port).append(":").append(sid).toString();
        } else {
            throw new SQLException("Invalid Port Number");
        }
    }

    /**
     * Sets up the connection to the Oracle DB == connection.getInstance()
     *
     * @param db_host
     * @param userName
     * @param userPass
     * @return Whether the connection succeeded or not
     */
    public boolean createOracleConnection(String db_host, String userName, String userPass) {
        System.out.println("--------------------- Starting Oracle connection ---------------------");
        return createConnection("oracle.jdbc.driver.OracleDriver", db_host, userName, userPass);
    }

    public boolean createMySQLConnection(String db_host, String userName, String userPass) {
        System.out.println("--------------------- Starting MySQL connection ---------------------");
        return createConnection("org.gjt.mm.mysql.Driver", db_host, userName, userPass);
    }

    private boolean createConnection(String driverName, String hostUrl, String userName, String password){
        int retry = MAX_RETRY;

        try {
            Class.forName(driverName);
            this.dbConnectionUrl = this.createOracleUrl(hostUrl, this.port, this.sid);
            System.out.println(this.dbConnectionUrl);
        } catch (ClassNotFoundException e) {
            System.out.println("JBDC Driver Not Found");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("Driver found");
        Connection con = startConnection(this.dbConnectionUrl, userName, password);

        while (con == null && retry > 0) {
            con = startConnection(this.dbConnectionUrl, userName, password);
            retry--;
        }

        if (con == null) {
            System.out.println("Connection has not been established after all retries - return failure");
            return false;
        }

        this.connection = con;

        return true;
    }

    public Connection getConnection() {
        return this.connection;
    }

    private Connection startConnection(String dbConnectionUrl, String userName, String userPass) {
        try {
            Connection con = DriverManager.getConnection(dbConnectionUrl, userName, userPass);
            System.out.println("Successfully Connected");
            return con;
        } catch (SQLException e) {
            System.out.println("Connection failed \n");
            e.printStackTrace();
        }
        return null;
    }

    public boolean closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
                System.out.println("--------------------- Closed connection ---------------------");
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}

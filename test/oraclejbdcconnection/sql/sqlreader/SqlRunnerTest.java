package oraclejbdcconnection.sql.sqlreader;

import oraclejbdcconnection.sql.connection.JBDCSQLConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by who cares on 2/13/2017.
 */
class SqlRunnerTest {

    private static JBDCSQLConnection oraclecon;

    @BeforeAll
    public static void setup() {
        oraclecon = new JBDCSQLConnection();
        oraclecon.setPort(1521);
        oraclecon.setSid("orcl");
        boolean pass = oraclecon.createOracleConnection("localhost", "test1", "pass1");

        assertTrue(pass);
    }

    @Test
    public void run_table_operations_file() {
        SqlRunner sqlr = SqlRunner.getInstance();
        sqlr.setConnection(oraclecon.getConnection());

        sqlr.run("resources/SQL_Scripts_test/create_table_test.sql");
        sqlr.run("resources/SQL_Scripts_test/insert_test.sql");
        sqlr.run("resources/SQL_Scripts_test/select_test.sql");
        sqlr.run("resources/SQL_Scripts_test/drop_table_test.sql");
    }

    @Test
    public void run_table_operations_query() {
        SqlRunner sqlr = SqlRunner.getInstance();
        sqlr.setConnection(oraclecon.getConnection());

        String create_query = "CREATE TABLE Persons_Query\n" +
                "(\n" +
                "    PersonID int,\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Address varchar(255),\n" +
                "    City varchar(255)\n" +
                ");";

        String insert_query = "INSERT INTO Persons_Query (PersonID, LastName, FirstName, Address, City)\n" +
                "VALUES (48, 'Fraser', 'Brendan', '123 Some st.', 'Indianapolis');\n" +
                "INSERT INTO Persons_Query (PersonID, LastName, FirstName, Address, City)\n" +
                "VALUES (64, 'Neeson', 'Liam', '123 Other st.', 'Ballymena');\n" +
                "INSERT INTO Persons_Query (PersonID, LastName, FirstName, Address, City)\n" +
                "VALUES (86, 'Eastwood', 'Clint', '123 Torino st.', 'San Francisco');";

        String select_query = "SELECT *\n" +
                "FROM Persons_Query\n" +
                "WHERE LastName = 'Fraser';";

        String drop_query = "DROP TABLE Persons_Query;";

        sqlr.run(create_query);
        sqlr.run(insert_query);
        sqlr.run(select_query);
        sqlr.run(drop_query);
    }

}
package database.sql.queryreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SqlRunner implements QueryRunner{
    private static final String DELIMITER = ";";
    private Connection connection;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static SqlRunner sqlReaderInstance;

    public static SqlRunner getInstance() {
        if (sqlReaderInstance == null) {
            sqlReaderInstance = new SqlRunner();
        }

        return sqlReaderInstance;
    }

    private SqlRunner() {}

    /**
     * return ResultSet from Query String
     *
     * @param query - Query in statement to execute
     * @return
     */
    @Override
    public PreparedStatement runquery(String query) {
        PreparedStatement currStatement;
        try {
            if (this.connection == null) {
                throw new SQLException("[E]: Driver Manager connection is null");
            }
            currStatement = this.connection.prepareStatement(query);
            return currStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * reads in an .sql file and runs the query/script in the SQL file
     *
     * @param source
     * @throws SQLException
     */
    @Override
    public PreparedStatement runfromfile(String source) throws SQLException {
        try {
            if (this.connection == null) {
                throw new SQLException("[E]: Driver Manager connection is null");
            }

            PreparedStatement currStatement = null;
            Scanner scanner = new Scanner(new File(source)).useDelimiter(DELIMITER);

            while (scanner.hasNext()) {
                String query = scanner.next() + DELIMITER;

                if (query.contains("E")) {
                    System.out.print("query: \n" + query + "\n\n");
                    query = query.substring(0, query.length() - 1);
                    currStatement = this.connection.prepareStatement(query);
                    if (!query.contains("SELECT"))
                        currStatement.execute();
                }
            }
            return currStatement;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * runFromFile insert, create or delete operation on
     *
     * @param source
     */
    public void run(String source) {
        PreparedStatement currStatement;
        try {
            if (source.contains(".sql")) {
                currStatement = runfromfile(source);
            } else {
                currStatement = runquery(source);
            }
            if (currStatement != null) {
                currStatement.executeUpdate();
                currStatement.close();
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage() + "\n");
        }
    }

    /**
     * same as run enter query as String
     *
     * @param source
     * @return
     */
    public ResultSet query(String source) {
        ResultSet results = null;
        PreparedStatement currStatement;
        try {
            if (source.contains(".sql"))
                currStatement = runfromfile(source);
            else
                currStatement = runquery(source);
            results = currStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * calls JBDCConnection class to connect to Oracle
     *
     * @param connection
     */
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<String> readTextFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        return Files.readAllLines(path, ENCODING);
    }

    @Override
    public void writeTextFile(List<String> aLines, String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        Files.write(path, aLines, ENCODING);
    }

}

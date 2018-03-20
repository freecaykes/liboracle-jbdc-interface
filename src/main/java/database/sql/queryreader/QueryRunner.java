package database.sql.queryreader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface QueryRunner {

    PreparedStatement runfromfile(String source) throws SQLException;
    PreparedStatement runquery(String query);

    void run(String source);

    void setConnection(Connection connection);

    List<String> readTextFile(String aFileName) throws IOException;
    void writeTextFile(List<String> aLines, String aFileName) throws IOException;
}

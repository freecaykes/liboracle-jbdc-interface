package database.sql.queryreader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NoSqlRunner implements QueryRunner{
    @Override
    public PreparedStatement runfromfile(String source) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement runquery(String query) {
        return null;
    }

    @Override
    public void run(String source) {

    }

    @Override
    public void setConnection(Connection connection) {

    }

    @Override
    public List<String> readTextFile(String aFileName) throws IOException {
        return null;
    }

    @Override
    public void writeTextFile(List<String> aLines, String aFileName) throws IOException {

    }
}

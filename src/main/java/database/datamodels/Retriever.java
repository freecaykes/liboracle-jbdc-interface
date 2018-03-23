package database.datamodels;

import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Retriever - Factory Design to fetch JBDC ResultSet as RetrievedSet
 *
 */
public class Retriever {

    public static RetrievedSet retrieveSet(String primaryKey, ResultSet rs, String attributes) throws DataModelException {
        return new RetrievedSet(primaryKey, rs, attributes);
    }


    public static class RetrievedSet {

        private JSONObject content;

        public RetrievedSet(String primaryKey, ResultSet rs, String attributes) throws DataModelException {
            parseResultSet(primaryKey, rs, attributes);
        }

        public RetrievedSet() {}

        /**
         * parse ResultSet Object from
         *
         * @param primaryKey
         * @param rs
         * @param attributes
         * @throws DataModelException
         */
        public void parseResultSet(String primaryKey, ResultSet rs, String attributes) throws DataModelException {
            String[] tableAttributes = attributes.split(";");

            if (rs == null) {
                throw new DataModelException("Empty ResultSet from Connection");
            }

            try {
                while (rs.next()) {
                    JSONObject row = new JSONObject();
                    String primaryAttribute = rs.getString(primaryKey);
                    for (int i = 0; i < tableAttributes.length; i++) {
                        row.put(tableAttributes[i], rs.getString(tableAttributes[i]));
                    }
                    content.put(primaryAttribute, row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public JSONObject getObject(String name) throws DataModelException {
            if (content == null) {
                throw new DataModelException("Empty content");
            }

            return (JSONObject) content.get(name);
        }

        public void removeObject(String key) throws DataModelException {
            if (content == null) {
                throw new DataModelException("Empty content");
            }

            content.remove(key);
        }

        public void writeObject(String key, JSONObject objectContent) throws DataModelException {
            if (content == null) {
                throw new DataModelException("Empty content");
            }

            content.put(key, objectContent);
        }

        public void clearContent() {
            if (content != null) {
                content = null;
            }
        }

    }
}

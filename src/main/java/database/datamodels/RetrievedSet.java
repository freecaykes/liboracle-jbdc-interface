package database.datamodels;

import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;



public class RetrievedSet {

    protected static JSONObject content;

    /**
     * parse ResultSet Object from
      * @param primaryKey
     * @param rs
     * @param attributes
     * @throws DataModelException
     */

    public static void parseResultSet(String primaryKey, ResultSet rs, String attributes) throws DataModelException {
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
                content.put( primaryAttribute, row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getObject(String name) throws DataModelException {
        if(content == null){
            throw new DataModelException("Empty content");
        }

        return (JSONObject) content.get(name);
    }

    public static void removeObject(String key) throws DataModelException {
        if(content == null){
            throw new DataModelException("Empty content");
        }

        content.remove(key);
    }

    public static void writeObject(String key, JSONObject objectContent) throws DataModelException {
        if(content == null){
            throw new DataModelException("Empty content");
        }

        content.put(key, objectContent);
    }

    public static void clearContent(){
        if(content != null){
            content = null;
        }
    }

}

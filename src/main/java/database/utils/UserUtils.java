package database.utils;

import java.util.LinkedList;
import java.util.List;

import database.datamodels.DataModelException;
import database.datamodels.Retriever;
import database.datamodels.User;
import database.security.EncryptUtils;
import database.sql.queryreader.SqlRunner;

public class UserUtils {

    private static final SqlRunner SQL_READER = SqlRunner.getInstance();
    private static final String DELIMITER = ";";


    public static User getUserFromDB(String userID, String userSchema, String idColumn, String definedAttributes) {
        User user = null;
        try {
            user = new User(userID, definedAttributes.split(DELIMITER), getUserAttributesFromDB(userID, userSchema, idColumn, definedAttributes));
        } catch (DataModelException e) {
            e.printStackTrace();
        }
        return user;
    }

    private static List<User.T> getUserAttributesFromDB(String userID,  String schema, String idName, String attributes) {
        final String QUERY_STRING = "SELECT * FROM" + schema + "WHERE" + idName + "= '" + userID + "'";
        List<User.T> attributeValues = new LinkedList<User.T>();
        try {
            String[] attributesArr = attributes.split(DELIMITER);
            Retriever.RetrievedSet rs = Retriever.retrieveSet(idName, SQL_READER.query(QUERY_STRING), attributes);

            for(String s:  attributesArr){
                String type = getColumnType(schema, s);
                User.T typeEnum = User.T.valueOf(type);
                attributeValues.add( (User.T) rs.getObject(userID).get(s));
            }

        } catch (DataModelException e) {
            e.printStackTrace();
        }

        return attributeValues;
    }

    private static String getColumnType(String table, String column) throws DataModelException {
        final String QUERY_STRING = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table + "' AND COLUMN_NAME = '" + column + "'";
        Retriever.RetrievedSet rs = Retriever.retrieveSet("DATA_TYPE", SQL_READER.query(QUERY_STRING), "DATA_TYPE");
        return (String) rs.getObject("DATA_TYPE").get("DATA_TYPE");
    }

    public static String getUserRole(User user, String userSchema, String roleTable, String attribute) {
        final String QUERY_STRING = "SELECT DISTINCT * FROM " + roleTable + " WHERE " + userSchema +  "=" + user.getUserID();
        try {
            Retriever.RetrievedSet rs = Retriever.retrieveSet(userSchema, SQL_READER.query(QUERY_STRING), attribute);
            return (String) rs.getObject(user.getUserID()).get(attribute);
        } catch (DataModelException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Compares a given password hash and the real password hash of the given user
     *
     * @param userID
     * @param givenPassword
     * @return If the given password matches the password on the DB
     */
    public static boolean isPasswordCorrect(String userID, String userColumnName, String givenPassword, String passwordColumnName) {
        final String QUERY_STRING = "SELECT passwordHash FROM Users WHERE userID = '" + userID + "'";

        String hashOfGivenPassword = hashPassword(givenPassword);
        try {
            Retriever.RetrievedSet rs = Retriever.retrieveSet(userColumnName, SQL_READER.query(QUERY_STRING), passwordColumnName);
            String realPasswordHash = (String) rs.getObject(passwordColumnName).get(passwordColumnName);
            return realPasswordHash.equals(hashOfGivenPassword); //hashOfGivenPassword can be null, but equals can deal with that
        } catch (IndexOutOfBoundsException ex) {
            return false;
        } catch (DataModelException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Hash secure pass
     *
     * @param userPass
     * @return The hash, or null if there is an error
     */
    public static String hashPassword(String userPass) {
        byte[] hashed = EncryptUtils.hash(userPass.toCharArray(), EncryptUtils.getSalt());
        return EncryptUtils.convertToHex(hashed);
    }
}

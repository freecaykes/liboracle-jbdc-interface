package database.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

import database.datamodels.DataModelException;
import database.datamodels.RetrievedSet;
import database.datamodels.User;
import database.sql.queryreader.SqlRunner;

public class UserUtils {

    private static final SqlRunner SQL_READER = SqlRunner.getInstance();


    public static User getUserFromDB(String userID, String userSchema, String idColumn) {
        User user = new User(userID,
                getUserAttributeFromDB(userID, userSchema, idColumn, "id"));
        return user;
    }

    private static String getUserAttributeFromDB(String userID, String idName, String schema, String attribute) {
        final String QUERY_STRING = "SELECT * FROM" + schema + "WHERE" + idName + "= '" + userID + "'";
        try {
            RetrievedSet.parseResultSet(idName, SQL_READER.query(QUERY_STRING), attribute);
            return (String) RetrievedSet.getObject(userID).get(attribute);
        } catch (DataModelException e) {
            e.printStackTrace();
        } finally {
            RetrievedSet.clearContent();
        }

        return null;
    }

    private static String getUserRole(String userID, String userSchema, String roleTable, String attribute) {
        final String QUERY_STRING = "SELECT DISTINCT * FROM " + roleTable + " WHERE " + userSchema +  "=" + userID;
        try {
            RetrievedSet.parseResultSet(userSchema, SQL_READER.query(QUERY_STRING), attribute);
            return (String) RetrievedSet.getObject(userID).get(attribute);
        } catch (DataModelException e) {
            e.printStackTrace();
        } finally {
            RetrievedSet.clearContent();
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
            RetrievedSet.parseResultSet(userColumnName, SQL_READER.query(QUERY_STRING), passwordColumnName);
            String realPasswordHash = (String) RetrievedSet.getObject(passwordColumnName).get(passwordColumnName);
            return realPasswordHash.equals(hashOfGivenPassword); //hashOfGivenPassword can be null, but equals can deal with that
        } catch (IndexOutOfBoundsException ex) {
            return false;
        } catch (DataModelException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Convert byte array to a (caps) hex string
     * Taken from http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
     *
     * @param bytes
     */
    private static String convertToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Hash secure pass
     *
     * @param userPass
     * @return The hash, or null if there is an error
     */
    public static String hashPassword(String userPass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(userPass.getBytes("UTF-8"), 0, userPass.length());
            return convertToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e){
            return null;
        }
    }
}

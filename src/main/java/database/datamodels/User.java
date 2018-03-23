package database.datamodels;

import java.util.HashMap;
import java.util.List;

public class User {

    private String userID;

    public enum T {String, Integer, Double, Float, Currency, Date, Byte}

    private HashMap<String, T> attributeMap = new HashMap();


    public User(String id, String[] attributes, List<T> attributeValues) throws DataModelException {
        userID = id;

        if(attributes.length != attributeValues.size()){
            throw new DataModelException("Column names ");
        }

        for(int i = 0; i< attributes.length; i++){
            attributeMap.put(attributes[i], attributeValues.get(i));
        }
    }

    public String getUserID() {
        return userID;
    }

    public T getAtrribute(String key){
        return attributeMap.get(key);
    }
}

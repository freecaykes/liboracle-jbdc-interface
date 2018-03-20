package database.datamodels;

public class User {

    private String userID;

    public User(String id, String attributes){
        userID = id;

    }

    public String getUserID() {
        return userID;
    }
}

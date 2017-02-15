package oraclejbdcconnection.datamodels;

@SuppressWarnings("serial")
public class User {
    private final String userID;
    private final boolean admin;
    private String hashedPassword;

    public User(String userID, boolean admin, String hashedPassword) {
        this.userID = userID;
        this.admin = admin;
        this.hashedPassword = hashedPassword;
    }

    public String getUserID() {
        return userID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}

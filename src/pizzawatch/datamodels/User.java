package pizzawatch.datamodels;

@SuppressWarnings("serial")
public class User
{
    private final String userID;
    private final boolean admin;
    private final String name;
    private final String creditCardNumber;

    public User(String userID, boolean admin, String name, String creditCardNumber)
    {
        this.userID = userID;
        this.admin = admin;
        this.name = name;
        this.creditCardNumber = creditCardNumber;
    }

    public String getUserID()
    {
        return userID;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public String getName()
    {
        return name;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }
}

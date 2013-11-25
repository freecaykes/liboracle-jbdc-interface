package pizzawatch.datamodels;

@SuppressWarnings("serial")
public class User
{
    private final String userID;
    private final boolean isAdmin;
    private final String name;
    private final String creditCardNumber;

    public User(String userID, boolean isAdmin, String name, String creditCardNumber)
    {
        this.userID = userID;
        this.isAdmin = isAdmin;
        this.name = name;
        this.creditCardNumber = creditCardNumber;
    }

    public String getUserID()
    {
        return userID;
    }

    public boolean isIsAdmin()
    {
        return isAdmin;
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

package pizzawatch.datamodels;

@SuppressWarnings("serial")
public class User
{
    private final String userID;
    private final boolean admin;
    private final String firstName;
    private final String lastName;
    private final String creditCardNumber;

    public User(String userID, boolean admin, String firstName, String lastName, String creditCardNumber)
    {
        this.userID = userID;
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }
}

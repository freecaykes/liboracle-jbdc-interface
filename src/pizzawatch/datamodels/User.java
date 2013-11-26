package pizzawatch.datamodels;

@SuppressWarnings("serial")
public class User
{
    private final String userID;
    private final boolean admin;
    private String firstName;
    private String lastName;
    private String creditCardNumber;

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

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }
}

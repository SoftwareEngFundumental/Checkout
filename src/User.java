import java.util.UUID;

public abstract class User
{
    private String userName;
    private String userPassword;

    public User(String userName)
    {
        this.userName = userName;
    }

    public String getUserName() { return userName; }

    public String getUserPassword() { return userPassword; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
}

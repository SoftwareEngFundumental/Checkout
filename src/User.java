import java.util.UUID;

public abstract class User
{
    private String userName;
    private String userPassword;
    private UUID   userUuid;


    public String getUserName() { return userName; }

    public String getUserPassword() { return userPassword; }

    public UUID   getUserUuid() { return userUuid; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public void setUserUuid(UUID uuid) { this.userUuid = uuid; }
}

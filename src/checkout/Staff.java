package checkout;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.UUID;

public abstract class Staff
{
    public Staff()
    {

    }

    private String  userName;
    private String  userPassword;
    private UUID    userUuid;
    private Boolean userLoginStatus;
    private StaffType userType;


    public String getUserName() { return userName; }

    public String getUserPassword() { return userPassword; }

    public UUID   getUserUuid() { return userUuid; }

    public Boolean getUserLoginStatus() { return userLoginStatus; }

    public StaffType getUserType() { return this.userType; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public void setUserUuid(UUID uuid) { this.userUuid = uuid; }

    public void setUserLoginStatus(Boolean userLoginStatus) { this.userLoginStatus = userLoginStatus; }

    public void setUserType(StaffType staffType) { this.userType = staffType; }
}

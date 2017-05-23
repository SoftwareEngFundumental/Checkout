package checkout.Staff;

public abstract class Staff
{
    private     String      userName;
    private     String      userPassword;
    private     int         userId;
    private     Boolean     userLoginStatus;
    private     StaffType   userType;


    public String       getUserName()                                   { return userName; }

    public String       getUserPassword()                               { return userPassword; }

    public int          getUserId()                                     { return userId; }

    public Boolean      getUserLoginStatus()                            { return userLoginStatus; }

    public StaffType    getUserType()                                   { return this.userType; }

    public void         setUserName(String userName)                    { this.userName = userName; }

    public void         setUserPassword(String userPassword)            { this.userPassword = userPassword; }

    public void         setUserId(int id)                               { this.userId = id; }

    public void         setUserLoginStatus(Boolean userLoginStatus)     { this.userLoginStatus = userLoginStatus; }

    public void         setUserType(StaffType staffType)                { this.userType = staffType; }
}

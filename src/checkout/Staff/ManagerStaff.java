package checkout.Staff;

public class ManagerStaff extends Staff
{
    public ManagerStaff(String userName, String userPassword, int userId)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserId(userId);
    }
}

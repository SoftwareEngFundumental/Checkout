package checkout;

import java.util.UUID;

public class SalesStaff extends Staff
{
    public SalesStaff(String userName, String userPassword, int userId)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserId(userId);
    }
}

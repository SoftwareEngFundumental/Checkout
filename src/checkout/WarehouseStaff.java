package checkout;

import java.util.UUID;

public class WarehouseStaff extends Staff
{
    public WarehouseStaff(String userName, String userPassword, int userId)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserId(userId);
    }
}

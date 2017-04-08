package checkout;

import java.util.UUID;

public class SalesStaff extends Staff
{
    public SalesStaff(String userName, String userPassword, UUID userUuid)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserUuid(userUuid);
    }
}

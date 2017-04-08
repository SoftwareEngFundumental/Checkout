package checkout;

import java.util.UUID;

public class ManagerStaff extends Staff
{
    public ManagerStaff(String userName, String userPassword, UUID userUuid)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserUuid(userUuid);
    }
}

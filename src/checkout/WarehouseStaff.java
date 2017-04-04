package checkout;

import java.util.UUID;

public class WarehouseStaff extends User
{
    public WarehouseStaff(String userName, String userPassword, UUID userUuid)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserUuid(userUuid);
    }
}

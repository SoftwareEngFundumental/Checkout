package checkout.Staff;

import checkout.Product.Product;

import java.util.ArrayList;

public class WarehouseStaff extends Staff
{
    public WarehouseStaff(String userName, String userPassword, int userId)
    {
        super.setUserName(userName);
        super.setUserPassword(userPassword);
        super.setUserId(userId);
    }
}


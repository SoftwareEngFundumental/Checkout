/**
 * Created by hu on 1/4/17.
 */
import java.io.*;
import java.util.*;

public class UserAuth
{
    public Boolean UserLogin(String userName, String userPassword)
    {
        return null;
    }

    public Dictionary<UUID,User> CreateUser(Dictionary<UUID,User> originalUserList, UserType userType,
                                      String userName, String userPassword)
    {
        UUID newUserGuid = UUID.randomUUID();
        User newUser = new SalesStaff(""); // Assign something to shut up the IDE
        Dictionary<UUID,User> newUserList;

        switch(userType)
        {
            case SALES:
            {
                newUser = new SalesStaff(userName);
                newUser.setUserPassword(userPassword);
                break;
            }
            case MANAGER:
            {
                newUser = new ManagerStaff(userName);
                newUser.setUserPassword(userPassword);
                break;
            }
            case CUSTOMER:
            {
                newUser = new Customer(userName, null);
                newUser.setUserPassword(userPassword);
            }
            case WAREHOUSE:
            {
                newUser = new WarehouseStaff(userName);
                newUser.setUserPassword(userPassword);
            }
        }

        originalUserList.put(newUserGuid, newUser);

        return originalUserList;
    }
}



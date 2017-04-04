package checkout;

import java.util.*;

public class UserManagement
{
    private ArrayList<User> userList = new ArrayList<User>();

    public ArrayList<User> getUserList() { return this.userList; }

    public void setUserList(ArrayList<User> userList) { this.userList = userList; }

    private User findUserFromList(String userName)
    {
        ArrayList<User> userList = getUserList();
        User user = null;

        for(User userToQuery : userList)
        {
            if(userToQuery.getUserName().equals(userName))
            {
                user = userToQuery;
            }
        }

        return user;
    }

    private User findUserFromList(UUID userUuid)
    {
        ArrayList<User> userList = getUserList();
        User user = null;

        for(User userToQuery : userList)
        {
            if(userToQuery.getUserUuid().equals(userUuid))
            {
                user = userToQuery;
            }
        }

        return user;
    }

    public Boolean userLogin(String userName, String userPassword)
    {
        ArrayList<User> userList = getUserList();
        User user = null;

        for(User userToQuery : userList)
        {
            if(userToQuery.getUserName().equals(userName))
            {
                user = userToQuery;
            }
        }

        // Return false because user name not found.
        if(user == null)
        {
            return false;
        }

        // Compare the password true or not, return the result directly!
        return user.getUserPassword().equals(userPassword);
    }

    public ArrayList<User> createUser(ArrayList<User> originalUserList, UserType userType,
                                      String userName, String userPassword)
    {
        // Generate user ID (UUID)
        UUID newUserUuid = UUID.randomUUID();
        User newUser;

        switch(userType)
        {
            case SALES:
            {
                newUser = new SalesStaff(userName, userPassword, newUserUuid);
                originalUserList.add(newUser);
                break;
            }
            case MANAGER:
            {
                newUser = new ManagerStaff(userName, userPassword, newUserUuid);
                originalUserList.add(newUser);
                break;
            }
            case WAREHOUSE:
            {
                newUser = new WarehouseStaff(userName, userPassword, newUserUuid);
                originalUserList.add(newUser);
                break;
            }
        }

        return originalUserList;
    }

    public Boolean deleteUser(UUID userUuid)
    {
        User user = findUserFromList(userUuid);

        return(user != null && this.getUserList().remove(user));
    }

    public Boolean deleteUser(String userName)
    {
        User user = findUserFromList(userName);

        return(user != null && this.getUserList().remove(user));
    }

    public Boolean changeUserPassword(UUID userUuid, String userOldPassword, String userNewPassword)
    {
        User user = findUserFromList(userUuid);

        // If old password not match then just return false to reject changes
        if(!(user.getUserPassword().equals(userOldPassword)))
        {
            return false;
        }
        else
        {
            user.setUserPassword(userNewPassword);
            return true;
        }
    }

    public void userLogout(String userName)
    {
        User user = findUserFromList(userName);
        if(user != null)
        {
            user.setUserLoginStatus(false);
        }
    }

    public void userLogout(UUID userUuid)
    {
        User user = findUserFromList(userUuid);
        if(user != null)
        {
            user.setUserLoginStatus(false);
        }
    }

}



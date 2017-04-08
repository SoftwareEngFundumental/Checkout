package checkout;

import java.util.*;

public class UserManagement
{
    private ArrayList<Staff> staffList = new ArrayList<>();

    public ArrayList<Staff> getStaffList() { return this.staffList; }

    public void setStaffList(ArrayList<Staff> staffList) { this.staffList = staffList; }

    private Staff findUserFromList(String userName)
    {
        ArrayList<Staff> staffList = getStaffList();
        Staff staff = null;

        for(Staff staffToQuery : staffList)
        {
            if(staffToQuery.getUserName().equals(userName))
            {
                staff = staffToQuery;
            }
        }

        return staff;
    }

    private Staff findUserFromList(UUID userUuid)
    {
        ArrayList<Staff> staffList = getStaffList();
        Staff staff = null;

        for(Staff staffToQuery : staffList)
        {
            if(staffToQuery.getUserUuid().equals(userUuid))
            {
                staff = staffToQuery;
            }
        }

        return staff;
    }

    public Boolean userLogin(String userName, String userPassword)
    {
        Staff staff = findUserFromList(userName);

        // Return false if staff name not found.
        if(staff == null)
        {
            return false;
        }

        // Set the status of password check
        Boolean passwordCheck = staff.getUserPassword().equals(userPassword);
        staff.setUserLoginStatus(passwordCheck);
        return passwordCheck;
    }

    public Boolean createUser(UserType userType, String userName, String userPassword)
    {
        // Generate user ID (UUID)
        UUID newUserUuid = UUID.randomUUID();
        Staff newStaff;

        if(findUserFromList(userName) == null)
        {
            switch(userType)
            {
                case SALES:
                {
                    newStaff = new SalesStaff(userName, userPassword, newUserUuid);
                    staffList.add(newStaff);
                    break;
                }
                case MANAGER:
                {
                    newStaff = new ManagerStaff(userName, userPassword, newUserUuid);
                    staffList.add(newStaff);
                    break;
                }
                case WAREHOUSE:
                {
                    newStaff = new WarehouseStaff(userName, userPassword, newUserUuid);
                    staffList.add(newStaff);
                    break;
                }
            }

            return true;
        }
        else
        {
            // Since this Staff is already existed in the list, it shouldn't be added into the list anymore.
            return false;
        }



    }

    public Boolean deleteUser(UUID userUuid)
    {
        Staff staff = findUserFromList(userUuid);

        return(staff != null && this.getStaffList().remove(staff));
    }

    public Boolean deleteUser(String userName)
    {
        Staff staff = findUserFromList(userName);

        return(staff != null && this.getStaffList().remove(staff));
    }

    public Boolean changeUserPassword(UUID userUuid, String userOldPassword, String userNewPassword)
    {
        Staff staff = findUserFromList(userUuid);

        // If old password not match then just return false to reject changes
        if(!(staff.getUserPassword().equals(userOldPassword)))
        {
            return false;
        }
        else
        {
            staff.setUserPassword(userNewPassword);
            return true;
        }
    }

    public Boolean changeUserPassword(String userName, String userOldPassword, String userNewPassword)
    {
        Staff staff = findUserFromList(userName);

        // If old password not match then just return false to reject changes
        if(!(staff.getUserPassword().equals(userOldPassword)))
        {
            return false;
        }
        else
        {
            staff.setUserPassword(userNewPassword);
            return true;
        }
    }

    public Boolean userLogout(String userName)
    {
        Staff staff = findUserFromList(userName);
        if(staff != null)
        {
            staff.setUserLoginStatus(false);
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean userLogout(UUID userUuid)
    {
        Staff staff = findUserFromList(userUuid);

        if(staff != null)
        {
            staff.setUserLoginStatus(false);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void saveUsersToFile(String filePath)
    {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.saveObjectToJsonFile(getStaffList(), filePath);
    }

    public void loadUsersFromFile(String filePath)
    {
        JsonDatabase jsonDatabase = new JsonDatabase();
        jsonDatabase.readObjectFromFile(filePath, staffList.getClass());
    }

}



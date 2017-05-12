package checkout.Staff;

import checkout.JsonDatabase;

import java.util.*;

public class StaffManagement
{
    private ArrayList<Staff> staffList = new ArrayList<>();

    public ArrayList<Staff> getStaffList() { return this.staffList; }

    public void setStaffList(ArrayList<Staff> staffList) { this.staffList = staffList; }

    public Staff findUserFromList(String userName)
    {
        ArrayList<Staff> staffList = getStaffList();
        Staff staff = null;

        // Find out the staff from the list
        // by iterate them one by one and match the username keyword.
        for(Staff staffToQuery : staffList)
        {
            if(staffToQuery.getUserName().equals(userName))
            {
                staff = staffToQuery;
            }
        }

        return staff;
    }

    public Staff findUserFromList(int userId)
    {
        ArrayList<Staff> staffList = getStaffList();
        Staff staff = null;

        // Find out the staff from the list
        // by iterate them one by one and match the user ID keyword.
        for(Staff staffToQuery : staffList)
        {
            if(staffToQuery.getUserId() == userId)
            {
                staff = staffToQuery;
            }
        }

        return staff;
    }

    public LoginResult userLogin(String userName, String userPassword)
    {
        Staff staff = findUserFromList(userName);

        // Return false if staff name not found.
        if(staff == null)
        {
            return new LoginResult(false, null);
        }

        // Set the status of password check
        Boolean passwordCheck = staff.getUserPassword().equals(userPassword);
        staff.setUserLoginStatus(passwordCheck);
        return new LoginResult(passwordCheck, staff);
    }

    public Boolean createUser(StaffType staffType, String userName, String userPassword)
    {
        // Generate user ID (int)
        int newUserId = getNewUserId(getStaffList());
        Staff newStaff;

        if(findUserFromList(userName) == null)
        {
            switch(staffType)
            {
                case SALES:
                {
                    newStaff = new SalesStaff(userName, userPassword, newUserId);
                    newStaff.setUserType(StaffType.SALES);
                    staffList.add(newStaff);
                    break;
                }
                case MANAGER:
                {
                    newStaff = new ManagerStaff(userName, userPassword, newUserId);
                    newStaff.setUserType(StaffType.MANAGER);
                    staffList.add(newStaff);
                    break;
                }
                case WAREHOUSE:
                {
                    newStaff = new WarehouseStaff(userName, userPassword, newUserId);
                    newStaff.setUserType(StaffType.WAREHOUSE);
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

    public Boolean deleteUser(int userId)
    {
        Staff staff = findUserFromList(userId);

        return(staff != null && this.getStaffList().remove(staff));
    }

    public Boolean deleteUser(String userName)
    {
        Staff staff = findUserFromList(userName);

        return(staff != null && this.getStaffList().remove(staff));
    }

    public Boolean changeUserPassword(int userId, String userOldPassword, String userNewPassword)
    {
        Staff staff = findUserFromList(userId);

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
        return _doUserLogout(staff);
    }

    public Boolean userLogout(int userId)
    {
        Staff staff = findUserFromList(userId);
        return _doUserLogout(staff);
    }

    private Boolean _doUserLogout(Staff staff)
    {
        if(staff != null)
        {
            if(!staff.getUserLoginStatus())
            {
                return false;
            }
            else
            {
                staff.setUserLoginStatus(false);
                return true;
            }
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
        this.setStaffList(jsonDatabase.readStaffListFromFile(filePath));
    }

    private int getNewUserId(ArrayList<Staff> userList)
    {
        // Create a user ID list to count
        ArrayList<Integer> userIdList = new ArrayList<>();

        for(Staff staffToQuery : userList)
        {
            userIdList.add(staffToQuery.getUserId());
        }

        // Sort it in ascend
        Collections.sort(userIdList);

        // If the user ID list does not have any user in it, return 0 because there is NO USER AT ALL!
        // Otherwise, find the largest user ID, then plus one to get the new ID.
        if(userIdList.size() == 0)
        {
            return 0;
        }
        else
        {
            return (userIdList.get(userIdList.size() - 1) + 1);
        }
    }

}



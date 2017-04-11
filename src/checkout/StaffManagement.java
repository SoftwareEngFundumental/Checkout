package checkout;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class StaffManagement
{
    private ArrayList<Staff> staffList = new ArrayList<>();

    public ArrayList<Staff> getStaffList() { return this.staffList; }

    public void setStaffList(ArrayList<Staff> staffList) { this.staffList = staffList; }

    public Type staffListType = new TypeToken<ArrayList<Staff>>() {}.getType();

    public Staff findUserFromList(String userName)
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

    public Staff findUserFromList(int userId)
    {
        ArrayList<Staff> staffList = getStaffList();
        Staff staff = null;

        for(Staff staffToQuery : staffList)
        {
            if(staffToQuery.getUserId() == userId)
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

    public Boolean userLogout(int userId)
    {
        Staff staff = findUserFromList(userId);

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
        ArrayList<Integer> userIdList = new ArrayList<>();

        for(Staff staffToQuery : userList)
        {
            userIdList.add(staffToQuery.getUserId());
        }

        Collections.sort(userIdList);
        return userIdList.get(userIdList.size() - 1);
    }

}



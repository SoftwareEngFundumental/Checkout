package test;

import static org.junit.Assert.*;

import checkout.util.JsonDatabase;
import checkout.Staff.Staff;
import checkout.Staff.StaffManagement;
import checkout.Staff.StaffType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import java.util.*;

/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 */

public class StaffManagementTest
{
    private StaffManagement staffManagement = null;

    @Before
    public void setUp() throws Exception
    {
        staffManagement = new StaffManagement();

        // Create three users for testing. Here we can't just use one user,
        // it may conflict with other test methods which will cause unexpected results.
        staffManagement.createUser(StaffType.WAREHOUSE, "Foo Delete", "f0o.bAr");
        staffManagement.createUser(StaffType.SALES, "Foo Password", "f0o.bAr");
        staffManagement.createUser(StaffType.MANAGER, "Foo Login", "f0o.bAr");
    }


    @Test
    public void testUserLogin() throws Exception
    {
        // Scenario #1: Correct password, should return true
        assertTrue(staffManagement.userLogin("Foo Login", "f0o.bAr").getLoginStatus());

        // Scenario #2: Incorrect password, should return false
        assertTrue(!staffManagement.userLogin("Foo Login", "who_the_hell_knows").getLoginStatus());

        // Clean up for future tests
        staffManagement.userLogout("Foo Login");
    }

    @Test
    public void testCreateUser() throws Exception
    {
        // Scenario #1: Create a user that does not exist in the list
        assertTrue(staffManagement.createUser(StaffType.MANAGER, "Foo Bar", "f0o.bAr"));

        // Scenario #2: Create a user that ALREADY EXISTS in the list
        assertTrue(!staffManagement.createUser(StaffType.MANAGER, "Foo Bar", "rand0m"));

        // Scenario #3: Find out the use from the list, should not be null
        assertNotNull(staffManagement.findUserFromList("Foo Bar"));
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        // Scenario #1.1: Delete this user from the list
        assertTrue(staffManagement.deleteUser("Foo Delete"));

        // Scenario #1.2: Check if the "Foo Delete" has been correctly deleted
        assertNull(staffManagement.findUserFromList("Foo Delete"));

        // Scenario #2: Delete a user that DOES NOT exist in the list
        assertTrue(!staffManagement.deleteUser("Foo Oops"));
    }


    @Test
    public void testChangeUserPassword() throws Exception
    {
        // Create a new user for testing
        staffManagement.createUser(StaffType.SALES, "Foo Bar", "f0o.bAr");

        // Scenario #1/2: Check if this user can set a new password,
        //  i.e. user exists and password must correct
        assertTrue(staffManagement.changeUserPassword("Foo Bar", "f0o.bAr", "fo0.baR"));

        // Scenario #2: If old password is not correct, check if it returns false result
        assertTrue(!staffManagement.changeUserPassword("Foo Bar", "O0ps!", "fo0.baR"));

        // Clean it up
        staffManagement.deleteUser("Foo Bar");
    }

    @Test
    public void testUserLogout() throws Exception
    {

        staffManagement.userLogin("Foo Login", "f0o.bAr");

        // Scenario #1: Try to logout, return true if loginStatus is correct.
        assertTrue(staffManagement.userLogout("Foo Login"));

        // Scenario #2: If the user has logged out, then it should return false.
        assertTrue(!staffManagement.userLogout("Foo Login"));

        // Scenario #3: If the user does not exist, then should also return false.
        assertTrue(!staffManagement.userLogout("Foo Null"));
    }


    @Test
    public void testSaveUsersToFile() throws Exception
    {
        staffManagement.saveUsersToFile("settings.json");

        // Scenario #1, see if the file correctly created and saved to correct location.
        File testFile = new File("settings.json");
        assertTrue(testFile.exists());

        // Scenario #2, see if the file correctly saved with valid contents.
        JsonDatabase jsonDatabase = new JsonDatabase();
        ArrayList<Staff> testListObject;

        testListObject = jsonDatabase.readStaffListFromFile("settings.json");
        assertTrue(testListObject.size() > 0);
    }

    @Test
    public void testLoadUsersFromFile() throws Exception
    {
        // Create a new staff for testing
        staffManagement.createUser(StaffType.WAREHOUSE, "Foo Bar Save", "f0o.bAr");

        // Save to file and then load it, then see if it works.
        staffManagement.saveUsersToFile("settings.json");

        // Load staffs from file
        staffManagement.loadUsersFromFile("settings.json");

        // Scenario #1: Try do a login
        assertTrue(staffManagement.userLogin("Foo Bar Save", "f0o.bAr").getLoginStatus());

        // Scenario #2: ry do a logout
        assertTrue(staffManagement.userLogout("Foo Bar Save"));
    }
}
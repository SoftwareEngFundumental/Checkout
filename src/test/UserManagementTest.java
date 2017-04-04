package test;

import static org.junit.Assert.*;
import checkout.User;
import checkout.UserManagement;
import checkout.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class UserManagementTest
{
    private UserManagement userManagement = null;
    private ArrayList<User> userList = null;

    @Before
    public void setUp() throws Exception
    {
        userManagement = new UserManagement();
        userList = new ArrayList<>();
        userList.addAll(userManagement.createUser(userList, UserType.SALES, "Foo Bar", "f0o.bAr"));
    }

    @After
    public void tearDown() throws Exception
    {
        // Do nothing?
    }


    @Test
    public void testUserLogin() throws Exception
    {
        assertTrue(userManagement.userLogin("Foo Bar", "f0o.bAr"));
    }

    @Test
    public void testCreateUser() throws Exception
    {
        userManagement.createUser(userList, UserType.SALES, "Bar Foo", "f0o.bAr");
        assertTrue(userList.size() > 1);
    }

    @Test
    public void testDeleteUser() throws Exception
    {
        assertTrue(userManagement.deleteUser("Bar Foo"));
    }


    @Test
    public void testChangeUserPassword() throws Exception
    {
        assertTrue(userManagement.changeUserPassword("Foo Bar", "f0o.bAr", "fo0.baR"));
    }

    @Test
    public void testUserLogout() throws Exception
    {
        userManagement.userLogout("Foo Bar");
    }


    @Test
    public void testSaveUsersToFile() throws Exception
    {
        userManagement.saveUsersToFile("settings.json");
    }

    @Test
    public void testLoadUsersFromFile() throws Exception
    {
        userManagement.loadUsersFromFile("settings.json");
    }
}
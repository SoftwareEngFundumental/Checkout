package checkout.Staff;


/**
 * Created and maintained by Ming Hu (s3554025) @ Semester 2017 for SEF Assignment
 *
 * So far login token contains the information of the staff and the login status.
 *
 * It is possible to be extended later on if necessary. e.g. adding login time and login allowance time period, etc.
 */
public class LoginToken
{
    private Boolean loginStatus;
    private Staff staff;

    public LoginToken(Boolean loginStatus, Staff staff)
    {
        this.loginStatus = loginStatus;
        this.staff = staff;
    }

    public Boolean getLoginStatus()
    {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus)
    {
        this.loginStatus = loginStatus;
    }

    public Staff getStaff()
    {
        return staff;
    }

    public void setStaff(Staff staff)
    {
        this.staff = staff;
    }
}

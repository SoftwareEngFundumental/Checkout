package checkout.Staff;


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

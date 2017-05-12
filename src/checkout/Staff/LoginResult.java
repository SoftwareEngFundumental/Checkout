package checkout.Staff;


public class LoginResult
{
    private Boolean loginStatus;
    private Staff staff;

    public LoginResult(Boolean loginStatus, Staff staff)
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

package checkout.util;

import java.util.Calendar;
import java.util.Date;
import java.time.*;

/**
 * I know there is a Period class in java.time but it does not directly fit with Date class.
 * Also it seems did not provide a comparison function in a certain date range.
 */
public class DatePeriod
{
    private Date earlierDate;
    private Date laterDate;

    public DatePeriod(Date earlierDate, Date laterDate)
    {
        this.earlierDate = earlierDate;
        this.laterDate = laterDate;
    }

    public DatePeriod(Calendar earlierCalendar, Calendar laterCalendar)
    {
        this.earlierDate = earlierCalendar.getTime();
        this.laterDate = laterCalendar.getTime();
    }

    public DatePeriod(int earlierYear, int earlierMonth, int earlierDate, int laterYear, int laterMonth, int laterDate)
    {
        Calendar earlierCal = Calendar.getInstance();
        Calendar laterCal = Calendar.getInstance();

        // Months are 0 - 11, i.e. January is 0 and December is 11
        // It's stupid huh? So, USE C# AND MAKE OBJECT-ORIENTED PROGRAMMING GREAT AGAIN!!
        earlierMonth = earlierMonth - 1;
        laterMonth = laterMonth - 1;

        earlierCal.set(earlierYear, earlierMonth, earlierDate);
        laterCal.set(laterYear, laterMonth, laterDate);

        this.earlierDate = earlierCal.getTime();
        this.laterDate = laterCal.getTime();
    }

    public static boolean isDateInDatePeriod(Date date, DatePeriod datePeriod)
    {
        return datePeriod.getEarlierDate().before(date) && datePeriod.getLaterDate().after(date);
    }

    public Date getEarlierDate()
    {
        return earlierDate;
    }

    public void setEarlierDate(Date earlierDate)
    {
        this.earlierDate = earlierDate;
    }

    public Date getLaterDate()
    {
        return laterDate;
    }

    public void setLaterDate(Date laterDate)
    {
        this.laterDate = laterDate;
    }
}

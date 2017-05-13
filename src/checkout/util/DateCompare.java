package checkout.util;

import java.util.*;

public class DateCompare
{
    public static boolean dateInDatePeriod(Date date, DatePeriod datePeriod)
    {
        return datePeriod.getEarlierDate().before(date) && datePeriod.getLaterDate().after(date);
    }
}

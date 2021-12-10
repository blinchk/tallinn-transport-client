package me.laus.tallinn_transport.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date secondsToDate(int seconds) {
        Calendar now = Calendar.getInstance();
        var date = new Date();
        now.setTime(date);
        var hours = Integer.valueOf(seconds / 3600);
        var minutesSeconds = seconds % 3600;
        now.set(Calendar.HOUR_OF_DAY, hours);
        now.set(Calendar.MINUTE, minutesSeconds / 60);
        now.set(Calendar.SECOND, minutesSeconds % 60);
        return Date.from(now.toInstant());
    }
}

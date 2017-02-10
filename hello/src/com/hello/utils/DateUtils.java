package com.hello.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class DateUtils
{

    public DateUtils()
    {
    }
    
    /**
	 * 当前timestamep
	 * @return
	 */
	public static Timestamp getNowTimestamep(){
		java.util.Date date = new java.util.Date();       
		Timestamp t = new Timestamp(date.getTime());
		return t;
	}
	
    /**获取指定时区的时区类*/
    private static TimeZone getTimezone(float timezero){
    	if (timezero > 13 || timezero < -12||timezero==0) {
        	timezero = 8;
        }
        int newTime=(int)(timezero * 60 * 60 * 1000);
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(newTime);
        if (ids.length == 0) {
            timeZone = TimeZone.getDefault();
        } else {
            timeZone = new SimpleTimeZone(newTime, ids[0]);
        }
    	return timeZone;
    }

    
    /**获取指定时区的指定格式的时间*/
    private static Date timezeroDate(float timezero,Date d,String format){
    	TimeZone timeZone=getTimezone(timezero);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(timeZone);
        String date=sdf.format(d);
        sdf=new SimpleDateFormat(format);
        try {
        	return sdf.parse(date);
		} catch (ParseException e) {
			return d;
		}
    }
    
	/**
	 * 
	 * @param 	timezero
	 * @return	时间格式："yyyyMMddHHmmss"
	 */
	public static String defaultTimezeroLong(float timezero){
		return timezeroString(timezero, new Date(),"yyyyMMddHHmmss");
	}
	/**
	 * 
	 * @param 	timezero
	 * @return	时间格式："yyyy-MM-dd HH:mm:ss"
	 */
	public static String defaultTimezeroString(float timezero){
		return timezeroString(timezero, new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 
	 * @param  timezero
	 * @param  timestamp
	 * @return 时间格式："yyyyMMddHHmmss"
	 */
	public static String definedTimezeroLong(float timezero,long timestamp){
		return timezeroString(timezero, new Date(timestamp),"yyyyMMddHHmmss");
	}
	/**
	 * 
	 * @param  timezero
	 * @param  timestamp
	 * @return 时间格式："yyyy-MM-dd HH:mm:ss"
	 */
	public static String timezeroString(float timezero,long timestamp){
		return timezeroString(timezero, new Date(timestamp),"yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 根据时区显示时间
	 * @param timezero
	 * @param d
	 * @param format	格式化
	 * @return
	 */
    public static String timezeroString(float timezero,Date d,String format){
        TimeZone timeZone=getTimezone(timezero);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(timeZone);
        return sdf.format(d);
    }
    /**将指定时间转化成对应时区的时间*/
    public static Date timezeroDate(float timezero,Date d){
    	return timezeroDate(timezero, d, "yyyy-MM-dd HH:mm:ss");
    }

    /** 根据时区转换当前的时间戳 */
    public static long convertTimeMillis(float timezero){
    	String time=defaultTimezeroString(timezero);
    	try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return System.currentTimeMillis();
    }
    
    /** 根据时区转换指定的时间戳 */
    public static long convertTimeMillis(float timezero,long time){
    	String convertTime=timezeroString(timezero,time);
    	try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(convertTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return System.currentTimeMillis();
    }

    public static Timestamp currentTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String now()
    {
        return now("yyyy-MM-dd HH:mm:ss");
    }

    public static String now(String format)
    {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat(format);
        return dateFm.format(date);
    }

    public static String time()
    {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("HH:mm:ss");
        return dateFm.format(date);
    }

    public static String date()
    {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(date);
    }

    public static String getDate()
    {
        return getDate("yyyyMMdd");
    }

    public static String getDate(String format)
    {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat(format);
        return dateFm.format(date);
    }

    public static String Time_Stamp()
    {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFm.format(date);
    }

    public static String MonthStart(String sDate)
    {
        return (new StringBuilder(String.valueOf(year(sDate)))).append("-").append(month(sDate)).append("-01").toString();
    }

    public static String WeekStart(String year, String week)
    {
        return WeekStart(toInt(year), toInt(week));
    }

    public static String WeekStart(int year, int week)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(3, week);
        cal.set(7, 1);
        if(year != cal.get(1))
            cal.set(1, year);
        return toString(cal.getTime());
    }

    public static String WeekEnd(String year, String week)
    {
        return WeekEnd(toInt(year), toInt(week));
    }

    public static String WeekEnd(int year, int week)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(3, week);
        cal.set(7, 7);
        return toString(cal.getTime());
    }

    public static String getDateFromTimestamp(String strDate)
    {
        String re[] = strDate.split(" ");
        return re[0];
    }

    public static String getShortTime(String strdate)
    {
        return getShortTime(((Date) (toTimestamp(strdate))));
    }

    public static String getShortTime(Date date)
    {
        SimpleDateFormat myFormatter = new SimpleDateFormat("HH:mm");
        return myFormatter.format(date);
    }

    public static boolean isYYear(int year)
    {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static int getYearWeek(String strDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toTimestamp(strDate));
        return calendar.get(3);
    }

    public static int getYear(String strDate)
    {
        return toInt(strDate.substring(0, 4));
    }

    public static String getYear2(String strDate)
    {
        return strDate.substring(0, 4).substring(2);
    }

    public static String getMonthString(String strDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(strDate));
        String re = String.valueOf(calendar.get(2) + 1);
        if(re.length() == 1)
            re = (new StringBuilder("0")).append(re).toString();
        return re;
    }

    public static int getMonth(String strDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(strDate));
        return calendar.get(2) + 1;
    }

    public static int getWeekDay(String strDate)
    {
        try
        {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date mydate = myFormatter.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mydate);
            return calendar.get(7);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean after(String date1, String date2)
    {
        Date d1 = toTimestamp(date1);
        Date d2 = toTimestamp(date2);
        if(d1 == null)
            return true;
        if(d2 == null)
            return false;
        else
            return d1.after(d2);
    }

    public static long interval(Date date1, Date date2)
    {
        if(date1 == null || date2 == null)
            return -1L;
        else
            return (date1.getTime() - date2.getTime()) / 86400000L;
    }

    public static long intervalsecounds(Date date1, Date date2)
    {
        if(date1 == null || date2 == null)
            return -1L;
        else
            return (date1.getTime() - date2.getTime()) / 1000L;
    }

    public static long intervalsecounds(String date1, String date2)
    {
        Date d1 = toTimestamp(date1);
        Date d2 = toTimestamp(date2);
        return intervalsecounds(d1, d2);
    }

    public static long interval(String date1, String date2)
    {
        Date d1 = toDate(date1);
        Date d2 = toDate(date2);
        return interval(d1, d2);
    }

    public static boolean before(String date1, String date2)
    {
        Date d1 = toTimestamp(date1);
        Date d2 = toTimestamp(date2);
        if(d1 == null)
            return false;
        if(d2 == null)
            return true;
        else
            return d1.before(d2);
    }

    public static Date toTimestamp(long l)
    {
        return new Timestamp(l);
    }

    public static String toString(Date date)
    {
        if(date == null)
        {
            return "";
        } else
        {
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
            return dateFm.format(date);
        }
    }

    public static String formatDate(Date date, String format)
    {
        if(date == null)
        {
            return "";
        } else
        {
            SimpleDateFormat dateFm = new SimpleDateFormat(format);
            return dateFm.format(date);
        }
    }

    public static String formatDate(String strDate, String format)
    {
        SimpleDateFormat dateFm = new SimpleDateFormat(format);
        Timestamp ts = toTimestamp(strDate);
        if(ts == null)
            return "";
        else
            return dateFm.format(ts);
    }

    public static Date toDate(long l)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        return calendar.getTime();
    }

    public static Timestamp toTimestamp(String str, String format)
    {
        try
        {
            SimpleDateFormat myFormatter = new SimpleDateFormat(format);
            return new Timestamp(myFormatter.parse(str).getTime());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Time toTime(String str)
    {
        try
        {
            if(str.split(":").length == 1)
                str = (new StringBuilder(String.valueOf(str))).append(":00:00").toString();
            if(str.split(":").length == 2)
                str = (new StringBuilder(String.valueOf(str))).append(":00").toString();
            SimpleDateFormat myFormatter = new SimpleDateFormat("HH:mm:ss");
            return new Time(myFormatter.parse(str).getTime());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp toTimestamp(String str)
    {
        if(str.equals(""))
            return null;
        if(str.indexOf(":") < 0)
            str = (new StringBuilder(String.valueOf(str))).append(" 00:00:00").toString();
        return toTimestamp(str, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static java.sql.Date toSqlDate(String str)
    {
        Date date = toDate(str);
        if(date == null)
            return null;
        else
            return new java.sql.Date(date.getTime());
    }

    public static Date toDate(String str)
    {
        if(str.equals(""))
            return null;
        try
        {
            SimpleDateFormat myFormatter = new SimpleDateFormat(str.indexOf(":") <= 0 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss");
            return myFormatter.parse(str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Date addDay(int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(date()));
        calendar.add(5, day);
        return calendar.getTime();
    }

    public static String addDayByNow(int day)
    {
        return formatDate(addDay(new Date(), day), "yyyy-MM-dd HH:mm:ss");
    }

    public static String addDayByDate(int day)
    {
        return formatDate(addDay(new Date(), day), "yyyy-MM-dd");
    }

    public static Date addDay(Date date, int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, day);
        return calendar.getTime();
    }

    public static Date addMinute(int minutes)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(System.currentTimeMillis()));
        calendar.add(12, minutes);
        return calendar.getTime();
    }

    public static Date addMinute(Date time, int minutes)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(12, minutes);
        return calendar.getTime();
    }

    public static Time addMinute(Time time, int minutes)
    {
        return new Time(time.getTime() + (long)(60000 * minutes));
    }

    public static String addMinute(String strDate, int minutes)
    {
        Date date = addMinute(toDate(strDate), minutes);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFm.format(date);
    }

    public static String addDay(String strDate, int day)
    {
        Date date = addDay(toDate(strDate), day);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(date);
    }

    public static Date addMonth(int month)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(date()));
        calendar.add(2, month);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month);
        return calendar.getTime();
    }

    public static String addMonth(String strDate, int month)
    {
        Date date = addMonth(toDate(strDate), month);
        SimpleDateFormat dateFm = new SimpleDateFormat(strDate.indexOf(":") <= 0 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss");
        return dateFm.format(date);
    }

    public static Timestamp addMonth(Timestamp time, int month)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time.getTime());
        calendar.add(2, month);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Date addYear(Date date, int year)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, year);
        return calendar.getTime();
    }

    public static String addYear(int year)
    {
        Date date = addYear(new Date(System.currentTimeMillis()), year);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(date);
    }

    public static String addYear(String strDate, int year)
    {
        Date date = addYear(toDate(strDate), year);
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        return dateFm.format(date);
    }

    public static int year(String strDate)
    {
        return year(toDate(strDate));
    }

    public static int year(Date date)
    {
        if(date == null)
        {
            return 1900;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(1);
        }
    }

    public static int week(String strDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(strDate));
        return calendar.get(3);
    }

    public static int week(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(7);
    }

    public static int week()
    {
        return week(new Date());
    }

    public static int hour()
    {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(11);
        return hour;
    }

    public static int year()
    {
        return year(new Date());
    }

    public static int month(String strDate)
    {
        return month(toDate(strDate));
    }

    public static int month(Date date)
    {
        if(date == null)
        {
            return 0;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(2) + 1;
        }
    }

    public static int month()
    {
        return month(new Date());
    }

    public static int day(String strDate)
    {
        return day(toDate(strDate));
    }

    public static int day(Date date)
    {
        if(date == null)
        {
            return 0;
        } else
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(5);
        }
    }

    public static int day()
    {
        return day(new Date());
    }

    public static int endDay(String year, String month)
    {
        return endDay(toInt(year, year()), toInt(month, month()));
    }

    public static int CHINESE_BIRTH_YEAR(int year)
    {
        int start = 1901;
        int x = (start - year) % 12;
        return (x + 12) % 12;
    }


    public static int endDay(int year, int month)
    {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        return c.getActualMaximum(5);
    }

    public static int CONSTELLATION(String birthdate)
    {
        return CONSTELLATION(month(birthdate), day(birthdate));
    }

    public static int CONSTELLATION(int month, int day)
    {
        int star = 0;
        if(month == 1 && day >= 20 || month == 2 && day <= 18)
            star = 11;
        if(month == 2 && day >= 19 || month == 3 && day <= 20)
            star = 12;
        if(month == 3 && day >= 21 || month == 4 && day <= 19)
            star = 1;
        if(month == 4 && day >= 20 || month == 5 && day <= 20)
            star = 2;
        if(month == 5 && day >= 21 || month == 6 && day <= 21)
            star = 3;
        if(month == 6 && day >= 22 || month == 7 && day <= 22)
            star = 4;
        if(month == 7 && day >= 23 || month == 8 && day <= 22)
            star = 5;
        if(month == 8 && day >= 23 || month == 9 && day <= 22)
            star = 6;
        if(month == 9 && day >= 23 || month == 10 && day <= 22)
            star = 7;
        if(month == 10 && day >= 23 || month == 11 && day <= 21)
            star = 8;
        if(month == 11 && day >= 22 || month == 12 && day <= 21)
            star = 9;
        if(month == 12 && day >= 22 || month == 1 && day <= 19)
            star = 10;
        return star;
    }

    public static int AGE(String birthdate)
    {
        return year() - year(birthdate);
    }

    @SuppressWarnings("all")
    public static Time[] timeSplit(Time startTime, Time endTime, int minutes)
    {
        List list = new ArrayList();
        for(Time curTime = startTime; curTime.before(endTime); curTime = addMinute(curTime, minutes))
            list.add(curTime);

        Time times[] = new Time[list.size()];
        return (Time[])list.toArray(times);
    }

    public static void main(String args[])
    {
        System.out.println(addDayByNow(1));
    }

    private static int toInt(String Str)
    {
        return toInt(Str, 0);
    }
    private static int toInt(String Str, int DefaultValue)
    {
        try
        {
            if(Str == null)
                return DefaultValue;
        }
        catch(Exception e)
        {
            return DefaultValue;
        }
        if(String.valueOf(Str).trim().length() == 0)
            return DefaultValue;
        String sValue = String.valueOf(Str).replaceAll(",", "");
        sValue = sValue.trim();
        int dot = sValue.indexOf(".");
        if(dot > 0)
            sValue = sValue.substring(0, dot);
        return Integer.parseInt(sValue);
    }

    
    
}



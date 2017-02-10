package com.hello.utils.help;

import java.util.Date;

/**
 * 缓存配置类
 * @author yanbo
 */
public class CacheObject
{
	private int intValue;
    private long longValue;
    private String StringValue;
    private Date DateValue;
    private long TimeOut;
    private long ActiveTime;
    private Object ObjectValue;

    public Object getObjectValue()
    {
        return ObjectValue;
    }

    public void setObjectValue(Object objectValue)
    {
        ObjectValue = objectValue;
    }

    public CacheObject(int value)
    {
        TimeOut = 300000L;
        ActiveTime = System.currentTimeMillis();
        intValue = value;
        ObjectValue = Integer.valueOf(value);
    }

    public CacheObject(long value)
    {
        TimeOut = 300000L;
        ActiveTime = System.currentTimeMillis();
        longValue = value;
        ObjectValue = Long.valueOf(value);
    }

    public CacheObject(String value)
    {
        TimeOut = 300000L;
        ActiveTime = System.currentTimeMillis();
        StringValue = value;
        ObjectValue = value;
    }

    public CacheObject(Object value)
    {
        TimeOut = 300000L;
        ActiveTime = System.currentTimeMillis();
        ObjectValue = value;
        if(value != null && (value instanceof String))
            StringValue = (String)value;
    }

    public CacheObject(int value, long TimeOut)
    {
        this.TimeOut = 300000L;
        ActiveTime = System.currentTimeMillis();
        intValue = value;
        this.TimeOut = TimeOut;
    }

    public CacheObject(long value, long TimeOut)
    {
        this.TimeOut = 300000L;
        ActiveTime = System.currentTimeMillis();
        longValue = value;
        this.TimeOut = TimeOut;
    }

    public CacheObject(String value, long TimeOut)
    {
        this.TimeOut = 300000L;
        ActiveTime = System.currentTimeMillis();
        StringValue = value;
        this.TimeOut = TimeOut;
    }


    public Date getDateValue()
    {
        return DateValue;
    }

    public void setDateValue(Date dateValue)
    {
        DateValue = dateValue;
    }

    public int getIntValue()
    {
        return intValue;
    }

    public void setIntValue(int intValue)
    {
        this.intValue = intValue;
    }

    public long getLongValue()
    {
        return longValue;
    }

    public void setLongValue(long longValue)
    {
        this.longValue = longValue;
    }

    public String getStringValue()
    {
        return StringValue;
    }

    public void setStringValue(String stringValue)
    {
        StringValue = stringValue;
    }

    public long getTimeOut()
    {
        return TimeOut;
    }

    public void setTimeOut(long timeOut)
    {
        if(TimeOut != 0L)
            TimeOut = timeOut;
    }

    public long getActiveTime()
    {
        return ActiveTime;
    }

    public void setActiveTime(long activeTime)
    {
        ActiveTime = activeTime;
    }

}


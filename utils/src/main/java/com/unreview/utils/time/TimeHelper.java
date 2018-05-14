package com.unreview.utils.time;

import com.unreview.model.bo.enums.TimeUnit;

public class TimeHelper {
    /**
     * 时间转换为毫秒（用职责连模式实现一下）
     * @param timeUnit
     * @param timeValue
     * @return
     * @throws Exception
     */
    public static long TimeConverToMillSeconds(TimeUnit timeUnit,long timeValue) throws Exception {
        if(timeUnit==TimeUnit.Millisecond)
            return timeValue;
        return SecondConverToMillSeconds(timeUnit, timeValue);
    }
    private static long SecondConverToMillSeconds(TimeUnit timeUnit,long timeValue) throws Exception {
        if(timeUnit==TimeUnit.Second)
            return timeValue*1000;
       return MinuteConverToMillSeconds(timeUnit,timeValue);
    }
    private static long MinuteConverToMillSeconds(TimeUnit timeUnit,long timeValue) throws Exception {
        if(timeUnit==TimeUnit.Minute)
            return timeValue*60*1000;
       return HourToMillSeconds( timeUnit, timeValue);
    }
    private static long HourToMillSeconds(TimeUnit timeUnit,long timeValue) throws Exception {
        if(timeUnit==TimeUnit.Hour)
            return timeValue*60*60*1000;
       return DayConverToMillSeconds( timeUnit, timeValue);
    }
    private static long DayConverToMillSeconds(TimeUnit timeUnit,long timeValue) throws Exception {
        if(timeUnit==TimeUnit.Day)
            return timeValue*24*60*60*1000;
        throw new Exception();
    }

}

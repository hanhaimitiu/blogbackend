package com.success.blogapi.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils {


    public static String format(String s) {
        return DateFormatUtils.format
                (Long.parseLong(s), "yyyy-MM-dd HH:mm:ss");
    }
}

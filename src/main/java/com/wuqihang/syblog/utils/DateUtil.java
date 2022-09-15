package com.wuqihang.syblog.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Wuqihang
 */
@Component
public class DateUtil {
    private final SimpleDateFormat formatter;

    public DateUtil() {
        this.formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public String millisToFormatDate(String millis) {
        long m;
        try {
            m = Long.parseLong(millis);
        } catch (Exception e) {
            m = 0;
        }
        return millisToFormatDate(m);
    }

    public String millisToFormatDate(long millis) {
        return formatter.format(new Date(millis));
    }

}

package org.example.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program:
 * @ClassName:
 * @description:
 * @author: zgc
 * @date:
 * @Version 1.0
 **/
public class DateUtils {
    // 获取当前的系统时间
    public static String getCurrentDate(){
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    
    //转换成日期时间
    public static String DateFormat(Date date){
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                String formattedDate = outputFormat.format(date);
                return formattedDate;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
    }


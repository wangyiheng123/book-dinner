package com.example.bookdinner.util;

import java.util.Calendar;
import java.util.Random;

/*
生成订单Id的工具类
 */
public class ItemIdUtil {

    private static final String StringBefore = "8222";

    private static final char[] num = new char[]{'0','1','2','3','4','5','6','7','8','9'};

    public static String getItemId(){
        Calendar calendar = Calendar.getInstance();
        //拿到当前年月日
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String date = String.valueOf(calendar.get(Calendar.DATE));
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        //随机生成6位数字
        for (int i = 0;i < 6;i++){
            int index = r.nextInt(10);
            stringBuilder.append(num[index]);
        }
        String randomNum = stringBuilder.toString();
        return StringBefore + year + month + date + randomNum;
    }

}

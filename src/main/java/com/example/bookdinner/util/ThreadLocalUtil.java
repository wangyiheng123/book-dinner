package com.example.bookdinner.util;

public class ThreadLocalUtil {

    private static final ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public static String getString(){
        return stringThreadLocal.get();
    }

    public static void setString(String str){
        stringThreadLocal.set(str);
    }

}

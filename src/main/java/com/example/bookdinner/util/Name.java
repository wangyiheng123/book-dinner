package com.example.bookdinner.util;

import java.util.Random;

/*
随机生成一个昵称
 */
public class Name {

    private static final char[] charNum = {'0','1','2','3','4','5','6','7','8','9'};

    public static String createName(){
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        for (int i = 0;i < 7;i++){
            int index = random.nextInt(8);
            name.append(charNum[index]);
        }
        return "用户" + name.toString();
    }

}

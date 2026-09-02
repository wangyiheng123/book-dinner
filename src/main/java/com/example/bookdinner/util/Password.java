package com.example.bookdinner.util;

import java.util.Random;

/*
刚注册后，随机生成一个密码
 */
public class Password {

    private static final char[] charCharacter = {'A','a','B','b','C','c','D','d','E','e','F','f','G','g','H','h','I','i','J','j','K','k','L','l','M','m','N','n','O','o','P','p','Q','q','R','r','S','s','T','t','U','u','V','v','W','w','X','x','Y','y','Z','z'};

    private static final char[] charNum = {'0','1','2','3','4','5','6','7','8','9'};

    private static final char[] charMark = {'*','.','@','!'};

    public static String createPassword(){
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0;i < 3;i++){
            int index = random.nextInt(52);
            password.append(charCharacter[index]);
        }
        for (int i = 0;i < 9;i++){
            int index = random.nextInt(10);
            password.append(charNum[index]);
        }
        int index = random.nextInt(4);
        password.append(charMark[index]);
        return password.toString();
    }
}

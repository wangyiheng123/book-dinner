package com.example.bookdinner.vo;
/*
给前端返回状态码的数据字典
 */
public class Code {

    public static final Integer REGISTER_OK = 20011;

    public static final Integer LOGIN_OK = 20021;

    public static final Integer UPDATE_OK = 20031;

    public static final Integer DELETE_OK = 20041;

    public static final Integer REGISTER_ERR = 20010;

    public static final Integer LOGIN_ERR = 20020;

    public static final Integer UPDATE_ERR = 20030;

    public static final Integer DELETE_ERR = 20040;

    public static final Integer SAVE_OK = 20051;

    public static final Integer SAVE_ERR = 20050;

    public static final Integer SELECT_OK = 20061;

    public static final Integer SELECT_NOTFOUND = 20060;

    public static final Integer SYSTEM_ERR = 50010;

    public static final Integer SERVICE_ERR = 50020;

    public static final Integer OTHER_ERR = 50030;

    public static final Integer EHCO_OK = 20071;

    public static final Integer EHCO_ERR = 20070;

    public static final Integer SEND_ERR = 50080;

    public static final Integer SEND_OK = 20081;

    public static final Integer NOT_LOGIN = 400100;

    public static final Integer LOGIN_LOSE = 400200;

    public static final Integer TOKEN_ERROR = 400300;

    public static final Integer LOCATE_OK = 400401;

    public static final Integer LOCATE_ERR = 400402;

    public static final Integer FILE_UPLOAD_SUCCESS = 20091;

    public static final Integer FILE_UPLOAD_FAIL = 20090;

    public static final Integer COMMON_LOGIN = 600100;

    public static final Integer ADMIN_LOGIN = 600200;

    public static final Integer SHOP_NOTFOUND_FOOD = 700100;

}

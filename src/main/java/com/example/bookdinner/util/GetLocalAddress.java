package com.example.bookdinner.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;

/*
获取本地地址信息的工具类
 */
public class GetLocalAddress {

    /*
    获取请求的ip地址
     */
    private static String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /*
    利用I/O去读取信息
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String getAddress(HttpServletRequest request) throws IOException {
        //辽源市IP
//        String ip = "222.160.109.191";
        //杭州市IP
//        String ip = "106.2.86.34";
        //兰州市IP
        String ip = "123.81.95.255";
        // 百度地图申请的ak
        String ak = "emqLnwINn0OL59fBE5GNqQQVjO33tLKx";
        // 这里调用百度的ip定位api服务 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ip="+ip+"&ak="+ak);
        //取所需数据
//        JSONObject obj = (JSONObject) ((JSONObject) json.get("content")).get("address_detail");
        JSONObject obj2 = (JSONObject) json.get("content");
        String address = obj2.getString("address");
        return address;
    }
}

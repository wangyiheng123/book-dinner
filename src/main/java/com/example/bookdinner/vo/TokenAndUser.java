package com.example.bookdinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
给前端返回一个既带token又带用户数据的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenAndUser {
    /*
    令牌
     */
    private String token;
    /*
    用户数据
     */
    private Object data;

}

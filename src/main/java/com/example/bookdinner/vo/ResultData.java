package com.example.bookdinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
存储给前端返回数据的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {
    /*
    给前端返回的状态码
     */
    private Integer code;
    /*
    给前端返回的数据
     */
    private Object data;
    /*
    给前端返回的信息
     */
    private String message;
}

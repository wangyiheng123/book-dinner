package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    private Integer id; //店铺ID

    private Integer cityId; //所属城市的ID

    private String image; //店铺图片

    private String name; //店铺名称

    private String location; //店铺位置

    private String sign; //店铺的个性签名

    private Float score; //店铺评分

    private Integer num; //店铺月售数量

    private Integer minute; //店铺配送时间

    private Float distance; //店铺距离

    private String created; //创建时间

    private Integer sendPrice; //起送金额

    private Integer status; //1为正常，0为删除

}

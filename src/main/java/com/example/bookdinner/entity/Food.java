package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
商品的实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private Integer id;

    private Integer shopId;

    private String name;

    private String image;

    private Float price;

    private Integer kindId;

    private Integer num;

    private String chargeMixture;

    private String mouthFeel;

    private String temperature;

    private String meatAndVegetables;

    private String method;

    private String minute;

    private String weight;

    private Integer status; //1为正常，0为删除

    private String numRanking; //月售量排名信息

    private Integer cartNum; //该商品在购物车中的数量



}

package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Integer id;

    private Integer userId;  //用户ID

    private Integer shopId;  //店铺ID

    private Integer foodId;  //商品ID

    private Integer num;    //商品数量

    private Food food; //存放商品信息

}

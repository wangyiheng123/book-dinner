package com.example.bookdinner.entity;
/*
购物车备份
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartBack {

    private Integer id;

    private Integer itemId;

    private Integer userId;

    private Integer shopId;

    private Integer foodId;

    private Integer num;

    private Food food; //存放商品信息

    public CartBack(Integer itemId, Integer userId, Integer shopId, Integer foodId, Integer num) {
        this.itemId = itemId;
        this.userId = userId;
        this.shopId = shopId;
        this.foodId = foodId;
        this.num = num;
    }
}

package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
评论实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advice {

    private Integer id; //评论Id

    private Integer userId; //用户Id

    private Integer itemId; //订单Id

    private Integer shopId; //店铺Id

    private Integer riderId; //骑手Id

    private Integer shopStar; //店铺所获星级

    private Integer riderStar; //骑手所获星级

    private String comment; //对店铺的评论

    private String created; //创建时间

    private Integer status; //0为删除 1为正常

    private List<String> imageList; //存储图片信息的src

    private User user; //存储用户信息

    private List<Integer> starList; //有几颗星就有多长

    public Advice(Integer userId, Integer itemId, Integer shopId, Integer riderId, Integer shopStar, Integer riderStar, String comment) {
        this.userId = userId;
        this.itemId = itemId;
        this.shopId = shopId;
        this.riderId = riderId;
        this.shopStar = shopStar;
        this.riderStar = riderStar;
        this.comment = comment;
    }
}

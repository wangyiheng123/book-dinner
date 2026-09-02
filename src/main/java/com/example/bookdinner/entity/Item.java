package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/*
订单对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Integer id; //主键Id

    private String itemId; //订单Id

    private Integer userId; //用户Id

    private Integer addressId; //地址Id

    private Integer riderId; //骑手Id

    private Integer shopId; //商店Id

    private String arriveMinute; //送达时间

    private Integer sendPrice; //配送费

    private String totalPrice; //订单总价

    private String mark; //备注信息

    private String tableNum; //餐具数量

    private String created; //创建时间

    private Integer status; //订单状态 0为已完成，1为配送中，2为删除

    private Integer minute; //配送时间

    private List<CartBack> cartList; //购物车中的商品集合

    private Shop shop; //存储商店信息

    private String statusString; //将状态码转换成文字


}

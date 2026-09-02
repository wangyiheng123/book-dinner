package com.example.bookdinner.vo;

import com.example.bookdinner.entity.Cart;
import com.example.bookdinner.entity.Shipping;
import com.example.bookdinner.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
准备提交订单页面的数据回显对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadySubmitItem {

    private Shipping shipping;

    private Shop shop;

    private List<Cart> list;

    private String arriveMinute;

    private Float total;

}

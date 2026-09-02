package com.example.bookdinner.vo;

import com.example.bookdinner.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
购物车中的列表与总钱数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListAndTotal {

    private List<Cart> list;

    private Float total;

}

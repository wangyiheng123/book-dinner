package com.example.bookdinner.service;

import com.example.bookdinner.entity.Cart;
import com.example.bookdinner.vo.ResultData;

public interface CartService {

    /*
    查询所有购物车中的商品信息
     */
    ResultData findAllCartByUserId(Integer userId,Integer shopId);

    /*
    添加购物车中的商品信息
     */
    ResultData addFoodInCart(Cart cart);

    /*
    删除购物车中的商品信息
     */
    ResultData deleteFoodInCart(Integer userId,Integer foodId);

    /*
    查询默认地址信息，购物车信息，商店信息
     */
    ResultData findCartItem(Integer userId,Integer shopId,Integer otherSendPrice);

}

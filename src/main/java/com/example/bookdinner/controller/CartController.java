package com.example.bookdinner.controller;

import com.example.bookdinner.entity.Cart;
import com.example.bookdinner.service.CartService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class CartController {

    @Resource
    private CartService cartService;

    /*
    查询购物车信息
     */
    @GetMapping("/cart/findAllCartByUserId/{userId}/{shopId}")
    public ResultData findAllCartByUserId(@PathVariable("userId") Integer userId, @PathVariable("shopId") Integer shopId){
        return cartService.findAllCartByUserId(userId, shopId);
    }

    /*
    添加购物车信息
     */
    @PostMapping("/cart/addFoodInCart")
    public ResultData addFoodInCart(@RequestBody Cart cart){
        return cartService.addFoodInCart(cart);
    }

    /*
    删除购物车中的信息
     */
    @GetMapping("/cart/deleteFoodInCart/{userId}/{foodId}")
    public ResultData deleteFoodInCart(@PathVariable("userId") Integer userId,@PathVariable("foodId") Integer foodId){
        return cartService.deleteFoodInCart(userId, foodId);
    }

    /*
    查询默认地址信息，购物车信息，商店信息
     */
    @GetMapping("/cart/findCartItem/{userId}/{shopId}/{otherSendPrice}")
    public ResultData findCartItem(@PathVariable("userId") Integer userId,@PathVariable("shopId") Integer shopId,@PathVariable("otherSendPrice") Integer otherSendPrice){
        return cartService.findCartItem(userId, shopId,otherSendPrice);
    }
}

package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.Cart;
import com.example.bookdinner.entity.Food;
import com.example.bookdinner.entity.Shipping;
import com.example.bookdinner.entity.Shop;
import com.example.bookdinner.mapper.CartMapper;
import com.example.bookdinner.mapper.FoodMapper;
import com.example.bookdinner.mapper.ShippingMapper;
import com.example.bookdinner.mapper.ShopMapper;
import com.example.bookdinner.service.CartService;
import com.example.bookdinner.vo.CartListAndTotal;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ReadySubmitItem;
import com.example.bookdinner.vo.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;
    @Resource
    private FoodMapper foodMapper;
    @Resource
    private ShippingMapper shippingMapper;
    @Resource
    private ShopMapper shopMapper;

    @Override
    public ResultData findAllCartByUserId(Integer userId, Integer shopId) {
        List<Cart> list = cartMapper.findAllCartByUserId(userId,shopId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"还没有添加购物车信息!");
        }
        float total = 0;
        for (Cart ca : list){
            Food food = foodMapper.findFoodById(ca.getFoodId());
            ca.setFood(food);
            total = total + ca.getNum() * ca.getFood().getPrice();
        }
        CartListAndTotal cartListAndTotal = new CartListAndTotal();
        cartListAndTotal.setList(list);
        cartListAndTotal.setTotal(total);
        return new ResultData(Code.SELECT_OK,cartListAndTotal,null);
    }

    @Override
    public ResultData addFoodInCart(Cart cart) {
        //1.查询购物车中是否存在这条商品的相关数据
        Integer ifExist = cartMapper.findAllCartByFoodId(cart.getUserId(),cart.getFoodId());
        //2.判断是否购物车中有相关数据
        if (ifExist == null){
            //3.如果没有，则添加
            int row = cartMapper.addFoodToCart(cart);
            if (row > 0){
                //添加成功
                return new ResultData(Code.SAVE_OK,null,null);
            }
            //添加失败
            return new ResultData(Code.SAVE_ERR,null,"添加购物车失败！");
        }else {
            //4.如果有，则修改商品在购物车中的数量
            int row = cartMapper.updateFoodNumInCart(cart.getUserId(), cart.getFoodId());
            if (row > 0){
                return new ResultData(Code.UPDATE_OK,null,null);
            }
            return new ResultData(Code.UPDATE_ERR,null,"修改购物车数量失败！");
        }
    }

    @Override
    public ResultData deleteFoodInCart(Integer userId, Integer foodId) {
        Integer ifExist = cartMapper.findAllCartByFoodId(userId, foodId);
        if (ifExist != null){
            if (ifExist == 1){
                int row = cartMapper.deleteFoodInCart(userId, foodId);
                if (row > 0){
                    return new ResultData(Code.DELETE_OK,null,null);
                }
                return new ResultData(Code.DELETE_ERR,null,null);
            }else {
                int row = cartMapper.updateFoodNumInCartReduce(userId, foodId);
                if (row > 0){
                    return new ResultData(Code.UPDATE_OK,null,null);
                }
                return new ResultData(Code.UPDATE_ERR,null,null);
            }
        }
        return null;
    }

    @Override
    public ResultData findCartItem(Integer userId, Integer shopId,Integer otherSendPrice) {
        float total = 0;
        if (userId != null){
            //根据用户Id查询默认地址信息
            Shipping shipping = shippingMapper.findDefaultByUserId(userId);
            if (shopId != null){
                //根据商店Id查询商店的信息
                Shop shop = shopMapper.findShopById(shopId);
                //根据用户Id和商店Id查询购物车信息
                List<Cart> list  = cartMapper.findAllCartByUserId(userId, shopId);
                //遍历购物车集合
                for (Cart ca : list){
                    //根据商品Id查询商品具体信息
                    Food food = foodMapper.findFoodById(ca.getFoodId());
                    int priceTimes = Math.round(food.getPrice() * ca.getNum()*100);
                    float a = (float) priceTimes / 100;
                    food.setPrice(a);
                    ca.setFood(food);
                    total = total + a;
                }
                //处理送达时间
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                long minute = shop.getMinute()*60*1000;
                long arriveMinute = date.getTime() + minute;
                String arriveMinuteString = simpleDateFormat.format(arriveMinute);
                //处理小计
                total = total + otherSendPrice;
                int againTotalTimes = Math.round(total * 100);
                total = (float) againTotalTimes / 100;
                return new ResultData(Code.SELECT_OK,new ReadySubmitItem(shipping,shop,list,arriveMinuteString,total),null);
            }
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关信息！");
    }
}

package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Cart;
import com.example.bookdinner.entity.CartBack;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    /*
    查询所有购物车的信息
     */
    @Select("select id,userId,shopId,foodId,num from cart where userId = #{userId} and shopId = #{shopId}")
    List<Cart> findAllCartByUserId(@Param("userId") Integer userId, @Param("shopId") Integer shopId);

    /*
    根据用户ID和商品ID查询商品在购物车中的数量
     */
    @Select("select num from cart where userId = #{userId} and foodId = #{foodId}")
    Integer findAllCartByFoodId(@Param("userId") Integer userId, @Param("foodId") Integer foodId);

    /*
    添加购物车的方法
     */
    @Insert("insert into cart values(null,#{userId},#{shopId},#{foodId},1)")
    int addFoodToCart(Cart cart);

    /*
    修改购物车当中的商品数量
     */
    @Update("update cart set num = num + 1 where userId = #{userId} and foodId = #{foodId}")
    int updateFoodNumInCart(@Param("userId") Integer userId, @Param("foodId") Integer foodId);

    /*
    删除购物车中的商品
     */
    @Delete("delete from cart where userId = #{userId} and foodId = #{foodId}")
    int deleteFoodInCart(@Param("userId") Integer userId, @Param("foodId") Integer foodId);

    /*
    修改购物车中的商品数量（减数）
     */
    @Update("update cart set num = num - 1 where userId = #{userId} and foodId = #{foodId}")
    int updateFoodNumInCartReduce(@Param("userId") Integer userId, @Param("foodId") Integer foodId);

    /*
    将购物车中的数据添加到备份数据库中
     */
    @Insert("insert into cartback values(null,#{itemId},#{userId},#{shopId},#{foodId},#{num})")
    int addCartToCartBack(CartBack cartBack);

    /*
    根据用户Id和商店Id删除购物车
     */
    @Delete("delete from cart where userId = #{userId} and shopId = #{shopId}")
    int deleteFoodInCartByUserIdShopId(@Param("userId") Integer userId, @Param("shopId") Integer shopId);

    /*
    根据订单Id查询购物车商品信息
     */
    @Select("select id,itemId,userId,shopId,foodId,num from cartback where itemId = #{itemId}")
    List<CartBack> findCartBackByItemId(@Param("itemId") Integer itemId);


}

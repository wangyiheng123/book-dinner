package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {

    /*
    添加订单
     */
    @Insert("insert into item values(null,#{itemId},#{userId},#{addressId},#{riderId},#{shopId},#{arriveMinute},#{sendPrice},#{totalPrice},#{mark},#{tableNum},now(),1)")
    int addItem(Item item);

    /*
    根据订单Id查询
     */
    @Select("select id,itemId,userId,addressId,riderId,shopId,arriveMinute,sendPrice,totalPrice,mark,tableNum,created,status from item where status != 2 and itemId = #{itemId}")
    Item findItemByItemId(@Param("itemId") String itemId);

    /*
    将订单状态修改成已完成
     */
    @Update("update item set status = 0 where itemId = #{itemId}")
    int updateItemStatus(@Param("itemId") String itemId);

    /*
    逻辑删除订单
     */
    @Update("update item set status = 2 where id = #{id}")
    int deleteItem(@Param("id") Integer id);

    /*
    根据用户Id查询订单信息
     */
    @Select("select id,itemId,userId,addressId,riderId,shopId,arriveMinute,sendPrice,totalPrice,mark,tableNum,created,status from item where status != 2 and userId = #{userId} order by created desc")
    List<Item> findItemsByUserId(@Param("userId") Integer userId);

    /*
    根据商店Id和用户Id查询订单信息
     */
    @Select({"<script>"+"select id,itemId,userId,addressId,riderId,shopId,arriveMinute,sendPrice,totalPrice,mark,tableNum,created,status from item where status != 2 and userId = #{userId} and shopId in"+ "<foreach item='item' index='index' collection='array' open='(' separator=',' close=')'>" + "#{item}" +"</foreach>" +"order by created desc"+"</script>"})
    List<Item> findItemByUserIdShopId(@Param("userId") Integer userId,@Param("array") int[] array);

    /*
    根据订单主键查询订单信息
     */
    @Select("select id,itemId,userId,addressId,riderId,shopId,arriveMinute,sendPrice,totalPrice,mark,tableNum,created,status from item where status != 2 and id = #{id}")
    Item findItemById(@Param("id") Integer id);

    /*
    将订单状态修改成已完成根据订单的主键Id
     */
    @Update("update item set status = 0 where id = #{id}")
    int updateItemStatusById(@Param("id") Integer id);

    /*
    根据骑手的Id查询订单信息
     */
    @Select("select id,itemId,userId,addressId,riderId,shopId,arriveMinute,sendPrice,totalPrice,mark,tableNum,created,status from item where status != 2 and riderId = #{riderId}")
    List<Item> findItemByRiderId(@Param("riderId") Integer riderId);

    /*
    根据骑手的Id查询订单数量
     */
    @Select("select count(id) from item where riderId = #{riderId}")
    int findCountByRiderId(@Param("riderId") Integer riderId);

}

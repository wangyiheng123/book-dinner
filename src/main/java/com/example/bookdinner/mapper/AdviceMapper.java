package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Advice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdviceMapper {

    /*
    添加评论
     */
    @Insert("insert into advice values(null,#{userId},#{itemId},#{shopId},#{riderId},#{shopStar},#{riderStar},#{comment},now(),1)")
    int addAdvice(Advice advice);

    /*
    评论添加过后，将添加过评论的订单记录
     */
    @Insert("insert into advicestatus values(null,#{userId},#{itemId})")
    void addAdviceStatus(@Param("userId") Integer userId,@Param("itemId") Integer itemId);

    /*
    查询哪些订单评论过
     */
    @Select("select itemId from advicestatus where userId = #{userId}")
    List<Integer> findItemIdFromAdviceStatus(@Param("userId") Integer userId);

    /*
    根据店铺Id查询所有评论
     */
    @Select("select id,userId,itemId,shopId,riderId,shopStar,riderStar,comment,created from advice where shopId = #{shopId} and status = 1 order By created desc")
    List<Advice> findAdviceByUserIdShopId(@Param("shopId") Integer shopId);

    /*
    根据用户Id查询所有评论
     */
    @Select("select id,userId,itemId,shopId,riderId,shopStar,riderStar,comment,created from advice where userId = #{userId} and status = 1 order By created desc")
    List<Advice> findAdviceByUserId(@Param("userId") Integer userId);

    /*
    逻辑删除评论
     */
    @Update("update advice set status = 0 where id = #{id}")
    int deleteAdviceById(@Param("id") Integer id);

    /*
    根据订单Id查询评论信息
     */
    @Select("select id,userId,itemId,shopId,riderId,shopStar,riderStar,comment,created from advice where itemId = #{itemId}")
    Advice findAdviceByItemId(@Param("itemId") Integer itemId);

}

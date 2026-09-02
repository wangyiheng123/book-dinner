package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Rider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RiderMapper {

    /*
    修改骑手的状态
     */
    @Update("update rider set status = 1 where id = #{id}")
    int updateRiderStatus(@Param("id") Integer id);

    /*
    查询空闲状态的骑手
     */
    @Select("select id,name,age,phone,status from rider where status = 0")
    List<Rider> findFreeRider();

    /*
    将骑手设置为休闲状态
     */
    @Update("update rider set status = 0 where id = #{id}")
    int updateRiderToFree(@Param("id") Integer id);

    /*
    根据骑手的Id查询骑手信息
     */
    @Select("select id,name,age,phone,status from rider where id = #{id}")
    Rider findRiderById(@Param("id") Integer id);

    /*
    查询所有骑手的信息
     */
    @Select("select id,name,age,phone,status from rider where status != 2")
    List<Rider> findAllRider();

    /*
    逻辑删除骑手
     */
    @Update("update rider set status = 2 where id = #{id}")
    int deleteRiderById(@Param("id") Integer id);

    /*
    添加骑手
     */
    @Insert("insert into rider values(null,#{name},#{age},#{phone},0)")
    int addRider(Rider rider);

    /*
    修改骑手信息
     */
    @Update("update rider set name = #{name},age = #{age},phone = #{phone} where id = #{id}")
    int updateRider(Rider rider);
}

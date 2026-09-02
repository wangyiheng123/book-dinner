package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Shipping;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShippingMapper {

    //模拟分页查询的方法
    @Select("select id,userId,address,sign,name,sex,phone,isDefault,status from shipping where status = 1 and userId = 2 limit 0,3 ")
    List<Shipping> testFindShipping();

    //添加地址信息
    @Insert("insert into shipping values(null,#{userId},#{address},#{sign},#{name},#{sex},#{phone},1,now(),1)")
    int addAddress(Shipping shipping);

    //查询地址信息
    @Select("select id,userId,address,sign,name,sex,phone,isDefault,status from shipping where status = 1 and userId = #{userId}")
    List<Shipping> findAddressByPage(@Param("userId") Integer userId);

    //修改成非默认地址
    @Update("update shipping set isDefault = 0 where userId = #{userId}")
    void updateNoDefault(@Param("userId") Integer userId);

    //修改成默认地址
    @Update("update shipping set isDefault = 1 where id = #{id}")
    int updateDefault(@Param("id") Integer id);

    //根据地址id查询地址信息并返回
    @Select("select id,userId,address,sign,name,sex,phone,isDefault,status from shipping where status = 1 and id = #{id}")
    Shipping findAddressById(@Param("id") Integer id);

    //根据id修改地址信息
    @Update("update shipping set address = #{address},sign = #{sign},name = #{name},sex = #{sex},phone = #{phone} where id = #{id}")
    int updateAddressById(Shipping shipping);

    //根据id逻辑删除删除地址信息
    @Update("update shipping set status = 0 where id = #{id}")
    int deleteAddressById(@Param("id") Integer id);

    //根据城市的名称查询城市的ID
    @Select("select id from town where city = #{city}")
    int findIdByCityName(@Param("city") String city);

    //查询默认地址信息
    @Select("select id,userId,address,sign,name,sex,phone,isDefault,status from shipping where userId = #{userId} and isDefault = 1 and status = 1")
    Shipping findDefaultByUserId(@Param("userId") Integer userId);

    @Select("select id,userId,address,sign,name,sex,phone,isDefault,status from shipping where id = #{id}")
    Shipping findAddressByIdNoNeedStatus(@Param("id") Integer id);
}

package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Food;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodMapper {

    /*
    根据店铺Id查询所有菜品
     */
    @Select("select id,shopId,name,image,price,kindId,num,chargeMixture,mouthFeel,temperature,meatAndVegetables,method,minute,weight from food where shopId = #{shopId} and status = 1 order by num desc")
    List<Food> findFoodList(@Param("shopId") Integer shopId);

    /*
    根据菜品Id查询菜品详情
     */
    @Select("select id,shopId,name,image,price,kindId,num,chargeMixture,mouthFeel,temperature,meatAndVegetables,method,minute,weight from food where id = #{id}")
    Food findFoodById(@Param("id") Integer id);

    /*
    根据菜品Id逻辑删除菜品信息
     */
    @Update("update food set status = 0 where id = #{id}")
    int deleteFoodById(@Param("id") Integer id);

    /*
    添加菜品信息
     */
    @Insert("insert into food values(null,#{shopId},#{name},#{image},#{price},#{kindId},0,#{chargeMixture},#{mouthFeel},#{temperature},#{meatAndVegetables},#{method},#{minute},#{weight},1)")
    int addFood(Food food);

    /*
    修改月售量
     */
    @Update("update food set num = num + #{num} where id = #{foodId}")
    void updateNumByFoodId(@Param("foodId") Integer foodId,@Param("num") Integer num);
}

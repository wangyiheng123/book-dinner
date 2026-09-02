package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Shop;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShopMapper {

    //根据城市的ID信息查询店铺信息
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where cityId = #{cityId} and status = 1")
    List<Shop> findShopByCityId(@Param("cityId") Integer cityId);

    //根据评分进行排序
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where cityId = #{cityId} and status = 1 order by score desc")
    List<Shop> findShopOrderByScore(@Param("cityId")Integer cityId);

    //根据配送距离进行排序
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where cityId = #{cityId} and status = 1 order by distance asc")
    List<Shop> findShopOrderByDistance(@Param("cityId")Integer cityId);

    //根据配送时间进行排序
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where cityId = #{cityId} and status = 1 order by minute asc")
    List<Shop> findShopOrderByMinute(@Param("cityId")Integer cityId);

    //根据月售量进行排序
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where cityId = #{cityId} and status = 1 order by num desc")
    List<Shop> findShopOrderByNum(@Param("cityId")Integer cityId);

    //根据创建时间进行排序
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where cityId = #{cityId} and status = 1 order by created desc")
    List<Shop> findShopOrderByCreated(@Param("cityId")Integer cityId);

    //根据关键字搜索
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where cityId = #{cityId} and status = 1 and name like #{key}")
    List<Shop> findShopByKey(@Param("cityId") Integer cityId,@Param("key") String key);

    //根据商店的ID查询商店的所有信息
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created,sendprice from shop where id = #{id}")
    Shop findShopById(@Param("id") Integer id);

    //模糊查询仅根据关键字
    @Select("select id,cityId,image,name,location,sign,score,num,minute,distance,created from shop where name like #{key} and status = 1")
    List<Shop> findShopByOnlyKey(@Param("key") String key);

    //查询营业执照的图片地址
    @Select("select image from shopimage where shopId = #{shopId}")
    String findShopImageByShopId(@Param("shopId") Integer shopId);

    //修改营业执照的地址
    @Update("update shopimage set image = #{image} where shopId = #{shopId}")
    int updateShopImageByShopId(@Param("shopId") Integer shopId,@Param("image") String image);

    //修改店铺的名称
    @Update("update shop set name = #{name} where id = #{shopId}")
    int updateShopNameByShopId(@Param("shopId") Integer shopId,@Param("name") String name);

    //修改店铺的地址
    @Update("update shop set location = #{address} where id = #{shopId}")
    int updateShopAddressByShopId(@Param("shopId") Integer shopId,@Param("address") String address);

    //修改店铺的标语
    @Update("update shop set sign = #{sign} where id = #{shopId}")
    int updateShopSignByShopId(@Param("shopId") Integer shopId,@Param("sign") String sign);

    //删除店铺信息
    @Update("update shop set status = 0 where id = #{shopId}")
    int deleteShopByShopId(@Param("shopId") Integer shopId);

    //添加店铺
    @Insert("insert into shop values(null,#{cityId},#{image},#{name},#{location},#{sign},#{score},#{num},#{minute},#{distance},now(),#{sendPrice},1)")
    int addShop(Shop shop);

    //添加营业执照
    @Insert("insert into shopimage values(null,#{shopId},#{image})")
    int addShopImage(@Param("shopId") Integer shopId,@Param("image") String image);

    //根据照片地址查询店铺Id
    @Select("select id from shop where image = #{image}")
    int findIdByImage(@Param("image") String image);

    //根据店铺Id查询店铺的名称
    @Select("select name from shop where id = #{shopId}")
    String findNameById(@Param("shopId") Integer shopId);

}

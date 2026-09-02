package com.example.bookdinner.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    /*
    存储图片的路径
     */
    @Insert("insert into file values(null,#{itemId},#{image})")
    int savePicture(@Param("itemId") Integer itemId, @Param("image") String image);

    /*
    根据订单Id查询所有图片路径
     */
    @Select("select image from file where itemId = #{itemId}")
    List<String> findImageSrcByItemId(@Param("itemId") Integer itemId);

}

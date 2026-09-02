package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Town;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TownMapper {

    /*
    查询所有的城市信息
     */
    @Select("select id,city from town")
    List<Town> findAllTown();

    /*
    根据城市Id查询城市名称
     */
    @Select("select city from town where id = #{id}")
    String findTownById(@Param("id") Integer id);

}

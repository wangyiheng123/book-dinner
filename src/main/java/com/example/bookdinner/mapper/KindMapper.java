package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.Kind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KindMapper {

    /*
    查询所有种类的信息
     */
    @Select("select id,kindName,num from kind")
    List<Kind> findAllKind();

    /*
    根据种类Id查询种类的名称
     */
    @Select("select kindName from kind where id = #{id}")
    String findKindNameById(@Param("id") Integer id);

}

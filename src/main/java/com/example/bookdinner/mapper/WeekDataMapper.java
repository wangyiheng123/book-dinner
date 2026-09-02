package com.example.bookdinner.mapper;

import com.example.bookdinner.entity.WeekData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeekDataMapper {

    @Select("select id,kindId,day,num from weekdata")
    List<WeekData> findAllWeekData();

}

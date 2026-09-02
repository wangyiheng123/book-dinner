package com.example.bookdinner.service;

import com.example.bookdinner.vo.ResultData;

public interface WeekDataService {

    /*
    查询各大菜系一周中每天的销量
     */
    ResultData findAllWeekData();

}

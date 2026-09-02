package com.example.bookdinner.controller;

import com.example.bookdinner.service.WeekDataService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WeekDataController {

    @Resource
    private WeekDataService weekDataService;

    @GetMapping("/weekData/findAllWeekData")
    public ResultData findAllWeekData(){
        return weekDataService.findAllWeekData();
    }

}

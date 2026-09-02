package com.example.bookdinner.controller;

import com.example.bookdinner.service.TownService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TownController {

    @Resource
    private TownService townService;

    /*
    查询所有的城市信息
     */
    @GetMapping("/town/findAllTown")
    public ResultData findAllTown(){
        return townService.findAllTown();
    }

}

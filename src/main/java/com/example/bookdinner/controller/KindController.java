package com.example.bookdinner.controller;

import com.example.bookdinner.service.KindService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class KindController {

    @Resource
    private KindService kindService;

    @GetMapping("/kind/findAllKind")
    public ResultData findAllKind(){
        return kindService.findAllKind();
    }

    @GetMapping("/kind/findAllKindIdAndName")
    public ResultData findAllKindIdAndName(){
        return kindService.findAllKindIdAndName();
    }

}

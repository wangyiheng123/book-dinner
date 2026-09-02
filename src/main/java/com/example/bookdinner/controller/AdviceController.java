package com.example.bookdinner.controller;

import com.example.bookdinner.service.AdviceService;
import com.example.bookdinner.vo.ItemIdAndAdviceData;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
评价模块的控制层
 */
@RestController
public class AdviceController {

    @Resource
    private AdviceService adviceService;

    /*
    添加评论的接口
     */
    @PostMapping("/advice/addAdvice")
    public ResultData addAdvice(@RequestBody ItemIdAndAdviceData itemIdAndAdviceData){
        return adviceService.addAdvice(itemIdAndAdviceData);
    }

    /*
    查询哪些订单评论过
     */
    @GetMapping("/advice/findItemIdFromAdviceStatus/{userId}")
    public ResultData findItemIdFromAdviceStatus(@PathVariable("userId") Integer userId){
        return adviceService.findItemIdFromAdviceStatus(userId);
    }

    /*
    查询店铺的所有评论
     */
    @GetMapping("/advice/findAdviceByUserIdShopId/{shopId}")
    public ResultData findAdviceByUserIdShopId(@PathVariable("shopId") Integer shopId){
        return adviceService.findAdviceByUserIdShopId(shopId);
    }

    /*
    查询用户的所有评论
     */
    @GetMapping("/advice/findAdviceByUserId/{userId}")
    public ResultData findAdviceByUserId(@PathVariable("userId") Integer userId){
        return adviceService.findAdviceByUserId(userId);
    }

    /*
    删除评论
     */
    @GetMapping("/advice/deleteAdviceById/{adviceId}/{userId}")
    public ResultData deleteAdviceById(@PathVariable("adviceId") Integer adviceId,@PathVariable("userId") Integer userId){
        return adviceService.deleteAdviceById(adviceId,userId);
    }

}

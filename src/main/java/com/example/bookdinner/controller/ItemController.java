package com.example.bookdinner.controller;

import com.example.bookdinner.entity.Item;
import com.example.bookdinner.service.ItemService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ItemController {

    @Resource
    private ItemService itemService;

    /*
    提交订单的接口
     */
    @PostMapping("/item/addItem")
    public ResultData addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    /*
    查询订单信息的接口
     */
    @GetMapping("/item/findItemsByUserId/{userId}")
    public ResultData findItemsByUserId(@PathVariable("userId") Integer userId){
        return itemService.findItemsByUserId(userId);
    }

    /*
    搜索框搜索订单的接口
     */
    @GetMapping("/item/findItemByUserIdBeLiked/{userId}/{key}")
    public ResultData findItemByUserIdBeLiked(@PathVariable("userId") Integer userId,@PathVariable("key") String key){
        return itemService.findItemByUserIdBeLiked(userId, key);
    }

    /*
    查询订单详情的接口
     */
    @GetMapping("/item/findItemDetail/{itemId}")
    public ResultData findItemDetail(@PathVariable("itemId") Integer itemId){
        return itemService.findItemDetail(itemId);
    }

    /*
    删除订单接口
     */
    @GetMapping("/item/deleteItemById/{id}/{userId}")
    public ResultData deleteItemById(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId){
        return itemService.deleteItemById(id,userId);
    }

    /*
    根据订单Id查询评价所需的回显信息
     */
    @GetMapping("/item/findAdviceNeedDataByItemId/{itemId}")
    public ResultData findAdviceNeedDataByItemId(@PathVariable("itemId") Integer itemId){
        return itemService.findAdviceNeedDataByItemId(itemId);
    }

    /*
    用户将订单设置为已经完成
     */
    @GetMapping("/item/updateItemStatus/{itemId}/{userId}")
    public ResultData updateItemStatus(@PathVariable("itemId") Integer itemId,@PathVariable("userId") Integer userId){
        return itemService.updateItemStatus(itemId, userId);
    }

    /*
    管理员删除订单
     */
    @GetMapping("/item/adminDeleteItemById/{id}/{riderId}")
    public ResultData adminDeleteItemById(@PathVariable("id") Integer id,@PathVariable("riderId") Integer riderId){
        return itemService.adminDeleteItemById(id, riderId);
    }

}

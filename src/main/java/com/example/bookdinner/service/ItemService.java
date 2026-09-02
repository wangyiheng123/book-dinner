package com.example.bookdinner.service;

import com.example.bookdinner.entity.Item;
import com.example.bookdinner.vo.ResultData;

public interface ItemService {

    /*
    添加订单
     */
    ResultData addItem(Item item);

    /*
    根据用户Id查询订单（按照创建时间倒序排列）
     */
    ResultData findItemsByUserId(Integer userId);

    /*
    模糊查询订单信息
     */
    ResultData findItemByUserIdBeLiked(Integer userId,String key);

    /*
    查询订单详情信息
     */
    ResultData findItemDetail(Integer itemId);

    /*
    逻辑删除订单
     */
    ResultData deleteItemById(Integer id,Integer userId);

    /*
    根据订单Id查询评价所需的回显信息
     */
    ResultData findAdviceNeedDataByItemId(Integer itemId);

    /*
    将订单设置为已经收货
     */
    ResultData updateItemStatus(Integer itemId,Integer userId);

    /*
    管理员删除订单
     */
    ResultData adminDeleteItemById(Integer id,Integer riderId);

}

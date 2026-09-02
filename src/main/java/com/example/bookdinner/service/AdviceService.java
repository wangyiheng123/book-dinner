package com.example.bookdinner.service;

import com.example.bookdinner.vo.ItemIdAndAdviceData;
import com.example.bookdinner.vo.ResultData;

public interface AdviceService {

    /*
    添加评论
     */
    ResultData addAdvice(ItemIdAndAdviceData itemIdAndAdviceData);

    /*
    查询哪些订单评论过
     */
    ResultData findItemIdFromAdviceStatus(Integer userId);

    /*
    查询店铺的所有评论
     */
    ResultData findAdviceByUserIdShopId(Integer shopId);

    /*
    查询用户的所有评论
     */
    ResultData findAdviceByUserId(Integer userId);

    /*
    删除评论
     */
    ResultData deleteAdviceById(Integer adviceId,Integer userId);

}

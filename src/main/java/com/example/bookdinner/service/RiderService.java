package com.example.bookdinner.service;

import com.example.bookdinner.entity.Rider;
import com.example.bookdinner.vo.ResultData;

public interface RiderService {

    /*
    查询所有的骑手信息
     */
    ResultData findAllRider();

    /*
    删除骑手
     */
    ResultData deleteRiderById(Integer id);

    /*
    添加骑手
     */
    ResultData addRider(Rider rider);

    /*
    根据骑手的Id查询骑手的信息
     */
    ResultData findRiderById(Integer id);

    /*
    修改骑手的信息
     */
    ResultData updateRider(Rider rider);

    /*
    查询骑手送过的订单
     */
    ResultData findItemByRiderId(Integer riderId);

}

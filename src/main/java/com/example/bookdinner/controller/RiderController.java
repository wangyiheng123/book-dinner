package com.example.bookdinner.controller;

import com.example.bookdinner.entity.Rider;
import com.example.bookdinner.service.RiderService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class RiderController {

    @Resource
    private RiderService riderService;

    /*
    查询所有骑手的信息
     */
    @GetMapping("/rider/findAllRider")
    public ResultData findAllRider(){
        return riderService.findAllRider();
    }

    /*
    删除骑手信息
     */
    @GetMapping("/rider/deleteRiderById/{id}")
    public ResultData deleteRiderById(@PathVariable("id") Integer id){
        return riderService.deleteRiderById(id);
    }

    /*
    添加骑手
     */
    @PostMapping("/rider/addRider")
    public ResultData addRider(@RequestBody Rider rider){
        return riderService.addRider(rider);
    }

    /*
    查询骑手的相关信息
     */
    @GetMapping("/rider/findRiderById/{id}")
    public ResultData findRiderById(@PathVariable("id") Integer id){
        return riderService.findRiderById(id);
    }

    /*
    修改骑手信息
     */
    @PostMapping("/rider/updateRider")
    public ResultData updateRider(@RequestBody Rider rider){
        return riderService.updateRider(rider);
    }

    /*
    查询骑手配送的订单信息
     */
    @GetMapping("/rider/findItemByRiderId/{riderId}")
    public ResultData findItemByRiderId(@PathVariable("riderId") Integer riderId){
        return riderService.findItemByRiderId(riderId);
    }
}

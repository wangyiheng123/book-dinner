package com.example.bookdinner.controller;

import com.example.bookdinner.entity.Shipping;
import com.example.bookdinner.service.ShippingService;
import com.example.bookdinner.vo.ResultData;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ShippingController {

    @Resource
    private ShippingService shippingService;

    @GetMapping("/shipping/getLocalAddress")
    public ResultData getLocalAddress(HttpServletRequest request){
        return shippingService.getLocalAddress(request);
    }

    /*
    测试接口
     */
    @PostMapping("/shipping/testFindShipping")
    public ResultData testFindShipping(){
        return shippingService.testFindShipping();
    }

    /*
    添加地址信息
     */
    @PostMapping("/shipping/addAddress")
    public ResultData addAddress(@RequestBody Shipping shipping){
        return shippingService.addAddress(shipping);
    }

    /*
    分页查询地址信息
     */
    @GetMapping("/shipping/findAddressByPage/{userId}/{pageNum}")
    public ResultData findAddressByPage(@PathVariable("userId") Integer userId, @PathVariable("pageNum") Integer pageNum){
        return shippingService.findAddressByPage(userId,pageNum,3);
    }

    /*
    修改默认地址
     */
    @GetMapping("/shipping/updateDefaultAddress/{id}/{userId}/{pageNum}")
    public ResultData updateDefaultAddress(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId,@PathVariable("pageNum") Integer pageNum){
        return shippingService.updateDefaultAddress(id, userId,pageNum);
    }

    /*
    根据id查询地址信息
     */
    @GetMapping("/shipping/findAddressById/{id}")
    public ResultData findAddressById(@PathVariable("id") Integer id){
        return shippingService.findAddressById(id);
    }

    /*
    根据id修改地址信息
     */
    @PostMapping("/shipping/updateAddressById")
    public ResultData updateAddressById(@RequestBody Shipping shipping){
        return shippingService.updateAddressById(shipping);
    }

    /*
    根据id逻辑删除地址信息
     */
    @GetMapping("/shipping/deleteAddressById/{userId}/{id}/{pageNum}")
    public ResultData deleteAddressById(@PathVariable("userId") Integer userId,@PathVariable("id") Integer id,@PathVariable("pageNum") Integer pageNum){
        return shippingService.deleteAddressById(userId, id, pageNum);
    }

    /*
    查询所有地址信息
     */
    @GetMapping("/shipping/findAllAddress/{userId}")
    public ResultData findAllAddress(@PathVariable("userId") Integer userId){
        return shippingService.findAllAddress(userId);
    }

    /*
    设置默认地址（不分页）
     */
    @GetMapping("/shipping/updateDefaultAddressNoByPage/{id}/{userId}")
    public ResultData updateDefaultAddressNoByPage(@PathVariable("id") Integer id,@PathVariable("userId") Integer userId){
        return shippingService.updateDefaultAddressNoByPage(id, userId);
    }

    /*
    查询默认地址信息
     */
    @GetMapping("/shipping/findDefaultAddress/{userId}")
    public ResultData findDefaultAddress(@PathVariable("userId") Integer userId){
        return shippingService.findDefaultAddress(userId);
    }

}

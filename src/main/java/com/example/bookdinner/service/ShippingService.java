package com.example.bookdinner.service;

import com.example.bookdinner.entity.Shipping;
import com.example.bookdinner.vo.ResultData;

import javax.servlet.http.HttpServletRequest;

public interface ShippingService {

    /*
    获取用户的地理位置信息
     */
    ResultData getLocalAddress(HttpServletRequest request);

    /*
    测试方法
     */
    ResultData testFindShipping();

    /*
    添加地址信息
     */
    ResultData addAddress(Shipping shipping);

    /*
    查询地址信息
     */
    ResultData findAddressByPage(Integer userId,Integer pageNum,Integer pageSize);

    /*
    修改成默认地址
     */
    ResultData updateDefaultAddress(Integer id,Integer userId,Integer pageNum);

    /*
    根据id查询地址的信息
     */
    ResultData findAddressById(Integer id);

    /*
    根据id修改地址信息
     */
    ResultData updateAddressById(Shipping shipping);

    /*
    根据id逻辑删除地址信息
     */
    ResultData deleteAddressById(Integer userId,Integer id,Integer pageNum);

    /*
    查询地址信息
     */
    ResultData findAllAddress(Integer userId);

    /*
    设置默认地址（不分页）
     */
    ResultData updateDefaultAddressNoByPage(Integer id,Integer userId);

    /*
    查询默认地址信息
     */
    ResultData findDefaultAddress(Integer userId);
}

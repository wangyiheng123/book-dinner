package com.example.bookdinner.controller;

import com.example.bookdinner.service.ShopService;
import com.example.bookdinner.vo.ResultData;
import com.example.bookdinner.vo.AddData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class ShopController {

    @Resource
    private ShopService shopService;

    @GetMapping("/shop/findShopByCityId/{cityId}")
    public ResultData findShopByCityId(@PathVariable("cityId") Integer cityId){
        return shopService.findShopByCityId(cityId);
    }

    @GetMapping("/shop/findShopOrderByDistance/{cityId}")
    public ResultData findShopOrderByDistance(@PathVariable("cityId") Integer cityId) {
        return shopService.findShopOrderByDistance(cityId);
    }

    @GetMapping("/shop/findShopOrderByMinute/{cityId}")
    public ResultData findShopOrderByMinute(@PathVariable("cityId") Integer cityId) {
        return shopService.findShopOrderByMinute(cityId);
    }

    @GetMapping("/shop/findShopOrderByScore/{cityId}")
    public ResultData findShopOrderByScore(@PathVariable("cityId") Integer cityId) {
        return shopService.findShopOrderByScore(cityId);
    }

    @GetMapping("/shop/findShopOrderByNum/{cityId}")
    public ResultData findShopOrderByNum(@PathVariable("cityId") Integer cityId) {
        return shopService.findShopOrderByNum(cityId);
    }

    @GetMapping("/shop/findShopOrderByCreated/{cityId}")
    public ResultData findShopOrderByCreated(@PathVariable("cityId") Integer cityId) {
        return shopService.findShopOrderByCreated(cityId);
    }

    @GetMapping("/shop/findShopByKey/{cityId}/{key}")
    public ResultData findShopByKey(@PathVariable("cityId") Integer cityId,@PathVariable("key") String key) {
        return shopService.findShopByKey(cityId,key);
    }

    @GetMapping("/shop/findShopById/{id}")
    public ResultData findShopById(@PathVariable("id") Integer id){
        return shopService.findShopById(id);
    }

    @GetMapping("/shop/findShopDetailByShopId/{shopId}")
    public ResultData findShopDetailByShopId(@PathVariable("shopId") Integer shopId){
        return shopService.findShopDetailByShopId(shopId);
    }

    @GetMapping("/shop/updateShopNameByShopId/{shopId}/{name}")
    public ResultData updateShopNameByShopId(@PathVariable("shopId") Integer shopId,@PathVariable("name") String name){
        return shopService.updateShopNameByShopId(shopId,name);
    }

    @GetMapping("/shop/updateShopAddressByShopId/{shopId}/{address}")
    public ResultData updateShopAddressByShopId(@PathVariable("shopId") Integer shopId,@PathVariable("address") String address){
        return shopService.updateShopAddressByShopId(shopId,address);
    }

    @GetMapping("/shop/updateShopSignByShopId/{shopId}/{sign}")
    public ResultData updateShopSignByShopId(@PathVariable("shopId") Integer shopId,@PathVariable("sign") String sign){
        return shopService.updateShopSignByShopId(shopId,sign);
    }

    @PostMapping("/shop/updateShopImageByShopId")
    public ResultData updateShopImageByShopId(MultipartFile file,Integer shopId){
        return shopService.updateShopImageByShopId(file, shopId);
    }

    @GetMapping("/shop/deleteShopByShopId/{shopId}/{cityId}")
    public ResultData deleteShopByShopId(@PathVariable("shopId") Integer shopId, @PathVariable("cityId") Integer cityId){
        return shopService.deleteShopByShopId(shopId, cityId);
    }

    @PostMapping("/shop/addShop")
    public ResultData addShop(@RequestBody AddData addData){
        return shopService.addShop(addData);
    }

    @GetMapping("/shop/findShopImageByShopId/{shopId}")
    public ResultData findShopImageByShopId(@PathVariable("shopId") Integer shopId){
        return shopService.findShopImageByShopId(shopId);
    }

    @GetMapping("/shop/findNameById/{shopId}")
    public ResultData findNameById(@PathVariable("shopId") Integer shopId){
        return shopService.findNameById(shopId);
    }
}

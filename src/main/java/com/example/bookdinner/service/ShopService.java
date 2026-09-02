package com.example.bookdinner.service;

import com.example.bookdinner.vo.ResultData;
import com.example.bookdinner.vo.AddData;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {

    //根据不同的城市，查询不同的店铺
    ResultData findShopByCityId(Integer cityId);

    //按照评分进行倒序排序
    ResultData findShopOrderByScore(Integer cityId);

    //按照配送距离进行升序排序
    ResultData findShopOrderByDistance(Integer cityId);

    //按照配送时间进行升序排序
    ResultData findShopOrderByMinute(Integer cityId);

    //按照月售量进行倒序排序
    ResultData findShopOrderByNum(Integer cityId);

    //按照创建时间进行倒序排序
    ResultData findShopOrderByCreated(Integer cityId);

    //根据关键字进行搜索
    ResultData findShopByKey(Integer cityId,String key);

    //根据商店的ID查询所有的商店信息
    ResultData findShopById(Integer id);

    //查询店铺的详细信息
    ResultData findShopDetailByShopId(Integer shopId);

    //修改店铺的名称
    ResultData updateShopNameByShopId(Integer shopId,String name);

    //修改店铺的地址
    ResultData updateShopAddressByShopId(Integer shopId,String address);

    //修改店铺的标语
    ResultData updateShopSignByShopId(Integer shopId,String sign);

    //修改店铺的营业执照
    ResultData updateShopImageByShopId(MultipartFile file, Integer shopId);

    //逻辑删除店铺的信息
    ResultData deleteShopByShopId(Integer shopId,Integer cityId);

    //添加店铺
    ResultData addShop(AddData addData);

    //查询营业执照的图片地址
    ResultData findShopImageByShopId(Integer shopId);

    //根据店铺Id查询店铺名称
    ResultData findNameById(Integer shopId);
}

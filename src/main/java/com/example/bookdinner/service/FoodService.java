package com.example.bookdinner.service;

import com.example.bookdinner.entity.Food;
import com.example.bookdinner.vo.AddFoodData;
import com.example.bookdinner.vo.ResultData;

public interface FoodService {

    /*
    根据商店的ID来查询商店中的商品列表
     */
    ResultData findFoodListByShopId(Integer shopId,Integer userId);

    /*
    根剧商品的Id查询商品的详细信息
     */
    ResultData findFoodDetailById(Integer id);

    /*
    根据菜品的Id删除菜品
     */
    ResultData deleteFoodById(Integer shopId,Integer foodId);

    /*
    添加菜品信息
     */
    ResultData addFood(AddFoodData addFoodData);

}

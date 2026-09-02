package com.example.bookdinner.controller;

import com.example.bookdinner.service.FoodService;
import com.example.bookdinner.vo.AddFoodData;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class FoodController {

    @Resource
    private FoodService foodService;

    @GetMapping("/food/findFoodListByShopId/{shopId}/{userId}")
    ResultData findFoodListByShopId(@PathVariable("shopId") Integer shopId,@PathVariable("userId") Integer userId){
        return foodService.findFoodListByShopId(shopId,userId);
    }

    @GetMapping("/food/findFoodDetailById/{id}")
    ResultData findFoodDetailById(@PathVariable("id") Integer id){
        return foodService.findFoodDetailById(id);
    }

    @GetMapping("/food/deleteFoodById/{shopId}/{foodId}")
    ResultData deleteFoodById(@PathVariable("shopId") Integer shopId,@PathVariable("foodId") Integer foodId){
        return foodService.deleteFoodById(shopId, foodId);
    }

    @PostMapping("/food/addFood")
    ResultData addFood(@RequestBody AddFoodData addFoodData){
        return foodService.addFood(addFoodData);
    }

}

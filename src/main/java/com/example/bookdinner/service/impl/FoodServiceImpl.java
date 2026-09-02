package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.Food;
import com.example.bookdinner.mapper.CartMapper;
import com.example.bookdinner.mapper.FoodMapper;
import com.example.bookdinner.mapper.KindMapper;
import com.example.bookdinner.service.FoodService;
import com.example.bookdinner.vo.AddFoodData;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.FoodAndKindName;
import com.example.bookdinner.vo.ResultData;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("foodService")
public class FoodServiceImpl implements FoodService {

    @Resource
    private FoodMapper foodMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private KindMapper kindMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultData findFoodListByShopId(Integer shopId,Integer userId) {
        List<Food> list = foodMapper.findFoodList(shopId);
        if (list.size() != 0){
            if (list.size() >= 3){
                for (int i = 0;i < 3;i++){
                    list.get(i).setNumRanking("门店销量第"+(i+1)+"名");
                }
            }
            for (Food food :list){
                Integer num = cartMapper.findAllCartByFoodId(userId,food.getId());
                if (num == null){
                    food.setCartNum(0);
                }else {
                    food.setCartNum(num);
                }
            }
            return new ResultData(Code.SELECT_OK,list,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"该店铺还没有上架商品！");
    }

    @Override
    public ResultData findFoodDetailById(Integer id) {
        Food food = foodMapper.findFoodById(id);
        if (food != null){
            String kindName = kindMapper.findKindNameById(food.getKindId());
            if (kindName != null){
                FoodAndKindName foodAndKindName = new FoodAndKindName();
                foodAndKindName.setFood(food);
                foodAndKindName.setKindName(kindName);
                return new ResultData(Code.SELECT_OK,foodAndKindName,null);
            }else {
                return new ResultData(Code.SELECT_NOTFOUND,food,"没有查到相关的种类信息");
            }
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到商品的相关信息");
    }

    @Override
    public ResultData deleteFoodById(Integer shopId, Integer foodId) {
        //逻辑删除菜品
        int row = foodMapper.deleteFoodById(foodId);
        if (row > 0){
            //删除成功后，进行查询
            List<Food> list = foodMapper.findFoodList(shopId);
            if (list.size() == 0){
                return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的菜品信息！");
            }
            return new ResultData(Code.DELETE_OK,list,"删除成功！");
        }
        return new ResultData(Code.DELETE_ERR,null,"删除失败！");
    }

    @Override
    public ResultData addFood(AddFoodData addFoodData) {
        //从redis数据库中取出图片信息
        String image = stringRedisTemplate.opsForValue().get("adminUser:"+addFoodData.getUserId()+":"+addFoodData.getShopId());
        //将数据封装进food中
        Food food = new Food();
        food.setShopId(addFoodData.getShopId());
        food.setName(addFoodData.getName());
        food.setImage(image);
        food.setPrice(addFoodData.getPrice());
        food.setKindId(addFoodData.getKindId());
        food.setChargeMixture(addFoodData.getChargeMixture());
        food.setMouthFeel(addFoodData.getMouthFeel());
        food.setTemperature(addFoodData.getTemperature());
        food.setMeatAndVegetables(addFoodData.getMeatAndVegetables());
        food.setMethod(addFoodData.getMethod());
        food.setMinute(addFoodData.getMinute());
        food.setWeight(addFoodData.getWeight());
        int row = foodMapper.addFood(food);
        if (row > 0){
            return new ResultData(Code.SAVE_OK,null,"添加菜品成功！");
        }
        return new ResultData(Code.SAVE_ERR,null,"添加菜品失败！");
    }
}

package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.*;
import com.example.bookdinner.mapper.*;
import com.example.bookdinner.service.RiderService;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ItemListAndStarNum;
import com.example.bookdinner.vo.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("riderService")
public class RiderServiceImpl implements RiderService {

    @Resource
    private RiderMapper riderMapper;

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private FoodMapper foodMapper;

    @Resource
    private AdviceMapper adviceMapper;

    @Override
    public ResultData findAllRider() {
        List<Rider> riderList = riderMapper.findAllRider();
        Collections.reverse(riderList);
        if (riderList.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到骑手信息！");
        }
        return new ResultData(Code.SELECT_OK,riderList,null);
    }

    @Override
    public ResultData deleteRiderById(Integer id) {
        int row = riderMapper.deleteRiderById(id);
        if (row > 0){
            //再次查询
            List<Rider> riderList = riderMapper.findAllRider();
            Collections.reverse(riderList);
            if (riderList.size() == 0){
                return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到骑手信息！");
            }
            return new ResultData(Code.DELETE_OK,riderList,null);
        }
        return new ResultData(Code.DELETE_ERR,null,"删除失败！");
    }

    @Override
    public ResultData addRider(Rider rider) {
        int row = riderMapper.addRider(rider);
        if (row > 0){
            //再次查询
            List<Rider> riderList = riderMapper.findAllRider();
            Collections.reverse(riderList);
            return new ResultData(Code.SAVE_OK,riderList,"添加骑手成功！");
        }
        return new ResultData(Code.SAVE_ERR,null,"添加骑手失败！");
    }

    @Override
    public ResultData findRiderById(Integer id) {
        Rider rider = riderMapper.findRiderById(id);
        if (rider == null){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到骑手的相关信息");
        }
        return new ResultData(Code.SELECT_OK,rider,null);
    }

    @Override
    public ResultData updateRider(Rider rider) {
        int row = riderMapper.updateRider(rider);
        if (row > 0){
            //再次查询
            List<Rider> riderList = riderMapper.findAllRider();
            Collections.reverse(riderList);
            return new ResultData(Code.UPDATE_OK,riderList,"修改信息成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改信息失败！");
    }

    @Override
    public ResultData findItemByRiderId(Integer riderId) {
        //根据骑手Id查询订单的信息
        List<Item> itemList = itemMapper.findItemByRiderId(riderId);
        List<ItemListAndStarNum> itemListAndStarNumList = new ArrayList<>();
        if (itemList.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"该骑手还没有送过订单！");
        }
        Collections.reverse(itemList);
        for (Item item : itemList){
            List<Integer> starList = null;
            //遍历集合，查询订单的商店信息
            Shop shop = shopMapper.findShopById(item.getShopId());
            item.setShop(shop);
            //查询该订单下的菜品信息
            List<CartBack> cartBackList = cartMapper.findCartBackByItemId(item.getId());
            for (CartBack cartBack : cartBackList){
                //根据菜品Id查询菜品信息
                Food food = foodMapper.findFoodById(cartBack.getFoodId());
                cartBack.setFood(food);
            }
            item.setCartList(cartBackList);
            if (item.getStatus() == 0){
                item.setStatusString("已完成");
            }else if (item.getStatus() == 1){
                item.setStatusString("配送中");
            }
            //拿到骑手的评价星级
            Advice advice = adviceMapper.findAdviceByItemId(item.getId());
            if (advice != null){
                int num = advice.getRiderStar();
                starList = new ArrayList<>();
                for (int i = 0;i < num;i++){
                    starList.add(0);
                }
            }
            ItemListAndStarNum itemListAndStarNum = new ItemListAndStarNum(item,starList);
            itemListAndStarNumList.add(itemListAndStarNum);
        }
        return new ResultData(Code.SELECT_OK,itemListAndStarNumList,null);
    }
}

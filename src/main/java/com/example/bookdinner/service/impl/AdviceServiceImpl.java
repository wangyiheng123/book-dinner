package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.Advice;
import com.example.bookdinner.entity.Item;
import com.example.bookdinner.entity.User;
import com.example.bookdinner.mapper.AdviceMapper;
import com.example.bookdinner.mapper.FileMapper;
import com.example.bookdinner.mapper.ItemMapper;
import com.example.bookdinner.mapper.UserMapper;
import com.example.bookdinner.service.AdviceService;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ItemIdAndAdviceData;
import com.example.bookdinner.vo.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("adviceService")
public class AdviceServiceImpl implements AdviceService {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private AdviceMapper adviceMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FileMapper fileMapper;

    @Override
    public ResultData addAdvice(ItemIdAndAdviceData itemIdAndAdviceData) {
        //从对象中拿出订单id
        int itemId = itemIdAndAdviceData.getItemId();
        //根据订单Id查询店铺Id和骑手Id
        Item item = itemMapper.findItemById(itemId);
        int userId = itemIdAndAdviceData.getUserId();
        int shopId = item.getShopId();
        int riderId = item.getRiderId();
        //将所需数据封装成Advice对象
        Advice advice = new Advice(userId,itemId,shopId,riderId,itemIdAndAdviceData.getShopStar(),itemIdAndAdviceData.getRiderStar(),itemIdAndAdviceData.getComment());
        //存入数据库中
        int row = adviceMapper.addAdvice(advice);
        if (row > 0){
            //添加评论成功后，将订单记录
            adviceMapper.addAdviceStatus(userId,itemId);
            return new ResultData(Code.SAVE_OK,null,"添加评价成功！");
        }
        return new ResultData(Code.SAVE_ERR,null,"添加评论失败！");
    }

    @Override
    public ResultData findItemIdFromAdviceStatus(Integer userId) {
        List<Integer> list = adviceMapper.findItemIdFromAdviceStatus(userId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,null);
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findAdviceByUserIdShopId(Integer shopId) {
        //根据店铺Id查询信息
        List<Advice> list = adviceMapper.findAdviceByUserIdShopId(shopId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"该店铺还没有评论");
        }
        for (Advice advice : list){
            //根据用户Id查询用户的信息
            User user = userMapper.findUserById(advice.getUserId());
            advice.setUser(user);
            int itemId = advice.getItemId();
            //根据订单Id查询上传的图片路径
            List<String> pathList = fileMapper.findImageSrcByItemId(itemId);
            advice.setImageList(pathList);
            List<Integer> starList = new ArrayList<>();
            for (int i = 0;i < advice.getShopStar();i++){
                starList.add(0);
            }
            advice.setStarList(starList);
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findAdviceByUserId(Integer userId) {
        //根据用户Id查询所有评论
        List<Advice> adviceList = adviceMapper.findAdviceByUserId(userId);
        if (adviceList.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"您还没有评论过！");
        }
        //根据用户Id查询用户信息
        User user = userMapper.findUserById(userId);
        for (Advice advice : adviceList){
            //根据订单Id去查询上传的图片路径
            List<String> pathList = fileMapper.findImageSrcByItemId(advice.getItemId());
            advice.setImageList(pathList);
            List<Integer> starList = new ArrayList<>();
            for (int i = 0;i < advice.getShopStar();i++){
                starList.add(0);
            }
            advice.setStarList(starList);
            advice.setUser(user);
        }
        return new ResultData(Code.SELECT_OK,adviceList,null);
    }

    @Override
    public ResultData deleteAdviceById(Integer adviceId,Integer userId) {
        int row = adviceMapper.deleteAdviceById(adviceId);
        if (row > 0){
            //根据用户的Id查询评论的信息
            List<Advice> adviceList = adviceMapper.findAdviceByUserId(userId);
            if (adviceList.size() == 0){
                return new ResultData(Code.SELECT_NOTFOUND,null,"您还没有评论过！");
            }
            //根据用户Id查询用户信息
            User user = userMapper.findUserById(userId);
            for (Advice advice : adviceList){
                //根据订单Id去查询上传的图片路径
                List<String> pathList = fileMapper.findImageSrcByItemId(advice.getItemId());
                advice.setImageList(pathList);
                List<Integer> starList = new ArrayList<>();
                for (int i = 0;i < advice.getShopStar();i++){
                    starList.add(0);
                }
                advice.setStarList(starList);
                advice.setUser(user);
            }
            return new ResultData(Code.DELETE_OK,adviceList,"删除成功！");
        }
        return new ResultData(Code.DELETE_ERR,null,"删除失败！");
    }
}

package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.Shipping;
import com.example.bookdinner.mapper.ShippingMapper;
import com.example.bookdinner.service.ShippingService;
import com.example.bookdinner.util.GetLocalAddress;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ResultData;
import com.example.bookdinner.vo.Town;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service("shippingService")
public class ShippingServiceImpl implements ShippingService {

    @Resource
    private ShippingMapper shippingMapper;

    @Override
    public ResultData getLocalAddress(HttpServletRequest request) {
        try {
            String address = GetLocalAddress.getAddress(request);
            if (address != null){
                int cityId = shippingMapper.findIdByCityName(address);
                System.out.println(cityId);
                return new ResultData(Code.LOCATE_OK,new Town(cityId,address),"定位成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultData(Code.LOCATE_ERR,null,"定位失败！");
    }

    @Override
    public ResultData testFindShipping() {
        return new ResultData(Code.SELECT_OK,shippingMapper.testFindShipping(),"查到了！");
    }

    @Override
    public ResultData addAddress(Shipping shipping) {
        shippingMapper.updateNoDefault(shipping.getUserId());
        int row = shippingMapper.addAddress(shipping);
        if (row > 0){
            return new ResultData(Code.SAVE_OK,null,"添加地址成功！");
        }
        return new ResultData(Code.SAVE_ERR,null,"添加地址失败！");
    }

    @Override
    public ResultData findAddressByPage(Integer userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy("created desc");
        List<Shipping> list = shippingMapper.findAddressByPage(userId);
        for (Shipping shipping : list){
            if (shipping.getIsDefault() == 1){
                shipping.setIsDefaultString("已为默认");
            }else {
                shipping.setIsDefaultString("设为默认");
            }
        }
        PageInfo<Shipping> pageInfo = new PageInfo<>(list);
        if (pageInfo.getList().size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,pageInfo,"没有数据！");
        }
        return new ResultData(Code.SELECT_OK,pageInfo,null);
    }

    @Override
    public ResultData updateDefaultAddress(Integer id,Integer userId,Integer pageNum) {
        shippingMapper.updateNoDefault(userId);
        int row = shippingMapper.updateDefault(id);
        if (row > 0){
            ResultData resultData = findAddressByPage(userId,pageNum,3);
            return new ResultData(Code.UPDATE_OK,resultData.getData(),null);
        }
        return new ResultData(Code.UPDATE_ERR,null,null);
    }

    @Override
    public ResultData findAddressById(Integer id) {
        Shipping shipping = shippingMapper.findAddressById(id);
        if (shipping != null){
            return new ResultData(Code.SELECT_OK,shipping,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"查询失败！");
    }

    @Override
    public ResultData updateAddressById(Shipping shipping) {
        int row = shippingMapper.updateAddressById(shipping);
        if (row > 0){
            return new ResultData(Code.UPDATE_OK,null,"修改成功！");
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData deleteAddressById(Integer userId, Integer id, Integer pageNum) {
        int row = shippingMapper.deleteAddressById(id);
        if (row > 0){
            ResultData resultData = findAddressByPage(userId,pageNum,3);
//            PageInfo<Shipping> pageInfo = (PageInfo<Shipping>) resultData.getData();
            return new ResultData(Code.DELETE_OK,resultData.getData(),null);
        }
        return new ResultData(Code.DELETE_ERR,null,"删除失败！");
    }

    @Override
    public ResultData findAllAddress(Integer userId) {
        List<Shipping> list = shippingMapper.findAddressByPage(userId);
        int index = 0;
        Shipping temp = new Shipping();
        if (list.size() != 0){
            for (int i = 0;i < list.size();i++){
                if (list.get(i).getIsDefault() == 1){
                    index = i;
                    temp = list.get(i);
                }
            }
            if (index != 0){
                list.remove(index);
                list.add(0,temp);
            }
            return new ResultData(Code.SELECT_OK,list,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"您还没有添加地址信息，请添加！");
    }

    @Override
    public ResultData updateDefaultAddressNoByPage(Integer id, Integer userId) {
        shippingMapper.updateNoDefault(userId);
        int row = shippingMapper.updateDefault(id);
        if (row > 0){
            return new ResultData(Code.UPDATE_OK,null,null);
        }
        return new ResultData(Code.UPDATE_ERR,null,"设置默认失败");
    }

    @Override
    public ResultData findDefaultAddress(Integer userId) {
        Shipping shipping = shippingMapper.findDefaultByUserId(userId);
        if (shipping != null){
            return new ResultData(Code.SELECT_OK,shipping,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"没有设置默认地址，请设置！");
    }
}

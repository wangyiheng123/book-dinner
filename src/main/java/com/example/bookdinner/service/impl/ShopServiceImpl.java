package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.Food;
import com.example.bookdinner.entity.Shop;
import com.example.bookdinner.mapper.FoodMapper;
import com.example.bookdinner.mapper.ShopMapper;
import com.example.bookdinner.mapper.TownMapper;
import com.example.bookdinner.service.ShopService;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ResultData;
import com.example.bookdinner.vo.AddData;
import com.example.bookdinner.vo.ShopNeedData;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private TownMapper townMapper;

    @Resource
    private FoodMapper foodMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultData findShopByCityId(Integer cityId) {
        List<Shop> list = shopMapper.findShopByCityId(cityId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到该城市的店铺信息！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findShopOrderByScore(Integer cityId) {
        List<Shop> list = shopMapper.findShopOrderByScore(cityId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到该城市的店铺信息！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findShopOrderByDistance(Integer cityId) {
        List<Shop> list = shopMapper.findShopOrderByDistance(cityId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到该城市的店铺信息！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findShopOrderByMinute(Integer cityId) {
        List<Shop> list = shopMapper.findShopOrderByMinute(cityId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到该城市的店铺信息！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findShopOrderByNum(Integer cityId) {
        List<Shop> list = shopMapper.findShopOrderByNum(cityId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到该城市的店铺信息！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findShopOrderByCreated(Integer cityId) {
        List<Shop> list = shopMapper.findShopOrderByCreated(cityId);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到该城市的店铺信息！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findShopByKey(Integer cityId, String key) {
        if (key == null){
            return new ResultData(Code.SELECT_NOTFOUND,null,"请填写查询的关键字！");
        }
        String valueKey = "%" + key + "%";
        List<Shop> list = shopMapper.findShopByKey(cityId,valueKey);
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"无查询记录！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }

    @Override
    public ResultData findShopById(Integer id) {
        Shop shop = shopMapper.findShopById(id);
        if (shop == null){
            return  new ResultData(Code.SELECT_NOTFOUND,null,"没有相关的店铺信息！");
        }
        return new ResultData(Code.SELECT_OK,shop,null);
    }

    @Override
    public ResultData findShopDetailByShopId(Integer shopId) {
        //根据店铺Id查询店铺的信息
        Shop shop = shopMapper.findShopById(shopId);
        if (shop == null){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的店铺信息！");
        }
        //根据店铺Id查询地址信息
        String cityName = townMapper.findTownById(shop.getCityId());
        //查询营业执照的照片地址
        String image = shopMapper.findShopImageByShopId(shopId);
        //查询该店铺信息下的菜品信息
        List<Food> foodList = foodMapper.findFoodList(shopId);
        if (foodList.size() == 0){
            return new ResultData(Code.SHOP_NOTFOUND_FOOD,new ShopNeedData(shop,image,foodList,cityName),"没有查询到菜品信息！");
        }
        return new ResultData(Code.SELECT_OK,new ShopNeedData(shop,image,foodList,cityName),null);
    }

    @Override
    public ResultData updateShopNameByShopId(Integer shopId, String name) {
        //修改店铺的名称
        int row = shopMapper.updateShopNameByShopId(shopId, name);
        if (row > 0){
            Shop shop = shopMapper.findShopById(shopId);
            if (shop == null){
                return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的店铺信息！");
            }
            //根据店铺Id查询地址信息
            String cityName = townMapper.findTownById(shop.getCityId());
            //查询营业执照的照片地址
            String image = shopMapper.findShopImageByShopId(shopId);
            //查询该店铺信息下的菜品信息
            List<Food> foodList = foodMapper.findFoodList(shopId);
            if (foodList.size() == 0){
                return new ResultData(Code.SHOP_NOTFOUND_FOOD,new ShopNeedData(shop,image,foodList,cityName),"没有查询到菜品信息！");
            }
            return new ResultData(Code.SELECT_OK,new ShopNeedData(shop,image,foodList,cityName),null);
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData updateShopAddressByShopId(Integer shopId, String address) {
        //修改店铺的地址
        int row = shopMapper.updateShopAddressByShopId(shopId, address);
        if (row > 0){
            Shop shop = shopMapper.findShopById(shopId);
            if (shop == null){
                return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的店铺信息！");
            }
            //根据店铺Id查询地址信息
            String cityName = townMapper.findTownById(shop.getCityId());
            //查询营业执照的照片地址
            String image = shopMapper.findShopImageByShopId(shopId);
            //查询该店铺信息下的菜品信息
            List<Food> foodList = foodMapper.findFoodList(shopId);
            if (foodList.size() == 0){
                return new ResultData(Code.SHOP_NOTFOUND_FOOD,new ShopNeedData(shop,image,foodList,cityName),"没有查询到菜品信息！");
            }
            return new ResultData(Code.SELECT_OK,new ShopNeedData(shop,image,foodList,cityName),null);
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData updateShopSignByShopId(Integer shopId, String sign) {
        //修改店铺的名称
        int row = shopMapper.updateShopSignByShopId(shopId, sign);
        if (row > 0){
            Shop shop = shopMapper.findShopById(shopId);
            if (shop == null){
                return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的店铺信息！");
            }
            //根据店铺Id查询地址信息
            String cityName = townMapper.findTownById(shop.getCityId());
            //查询营业执照的照片地址
            String image = shopMapper.findShopImageByShopId(shopId);
            //查询该店铺信息下的菜品信息
            List<Food> foodList = foodMapper.findFoodList(shopId);
            if (foodList.size() == 0){
                return new ResultData(Code.SHOP_NOTFOUND_FOOD,new ShopNeedData(shop,image,foodList,cityName),"没有查询到菜品信息！");
            }
            return new ResultData(Code.SELECT_OK,new ShopNeedData(shop,image,foodList,cityName),null);
        }
        return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
    }

    @Override
    public ResultData updateShopImageByShopId(MultipartFile file, Integer shopId) {
        if (file != null) {
            //获取文件的名字
            String originalFileName = file.getOriginalFilename();
            //获取文件的后缀名
            String subString = originalFileName.substring(originalFileName.lastIndexOf("."));
            //用UUID随机生成文件名
            String fileName = UUID.randomUUID().toString() + subString;
            //保存文件
            try {
                file.transferTo(new File("D:/arithmetic/book-dinner/src/main/resources/static/img/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String path = "/img/" + fileName;
            //修改地址信息
            int row = shopMapper.updateShopImageByShopId(shopId,path);
            if (row > 0){
                return new ResultData(Code.FILE_UPLOAD_SUCCESS,path,"文件上传成功！");
            }
            return new ResultData(Code.UPDATE_ERR,null,"修改失败！");
        }
        return new ResultData(Code.FILE_UPLOAD_FAIL,null,"文件上传失败！");
    }

    @Override
    public ResultData deleteShopByShopId(Integer shopId,Integer cityId) {
        int row = shopMapper.deleteShopByShopId(shopId);
        if (row > 0){
            List<Shop> list = shopMapper.findShopByCityId(cityId);
            if (list.size() == 0){
                return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到该城市的店铺信息！");
            }
            return new ResultData(Code.DELETE_OK,list,null);
        }
        return new ResultData(Code.DELETE_ERR,null,"删除失败！");
    }

    @Override
    public ResultData addShop(AddData addData) {
        //将shop对象拿出来
        Shop shop = new Shop();
        shop.setCityId(addData.getCityId());
        shop.setName(addData.getName());
        shop.setLocation(addData.getLocation());
        shop.setSign(addData.getSign());
        //将图片从redis数据库中拿出来
        String image = stringRedisTemplate.opsForValue().get("adminUser:image:" + addData.getUserId());
        String image1 = stringRedisTemplate.opsForValue().get("adminUser:image1:" + addData.getUserId());
        int[] num = new int[]{40,41,42,43,44,45,46,47,48,49,50};
        int[] min = new int[]{23,25,42,51,60,24,18,32,35,34,28,26};
        int[] priceArray = new int[]{18,28,30,19,24,26,17,23,21};
        Random r = new Random();
        //评分
        float score = (float)num[r.nextInt(11)] / 10;
        //月售量
        int monthNum = num[r.nextInt(11)] * 11;
        //配送时间
        int minute = min[r.nextInt(12)];
        //配送距离
        float distance = (float) min[r.nextInt(12)] / 10;
        //起送价格
        int sendPrice = priceArray[r.nextInt(9)];
        shop.setImage(image);
        shop.setScore(score);
        shop.setMinute(minute);
        shop.setNum(monthNum);
        shop.setSendPrice(sendPrice);
        shop.setDistance(distance);
        int row = shopMapper.addShop(shop);
        if (row > 0){
            //查询店铺Id
            int shopId = shopMapper.findIdByImage(image);
            //将营业执照存入数据库
            shopMapper.addShopImage(shopId,image1);
            return new ResultData(Code.SAVE_OK,null,"添加店铺成功！");
        }
        return new ResultData(Code.SAVE_ERR,null,"添加店铺失败！");
    }

    @Override
    public ResultData findShopImageByShopId(Integer shopId) {
        String image = shopMapper.findShopImageByShopId(shopId);
        if (image == null){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到营业执照的图片！");
        }
        return new ResultData(Code.SELECT_OK,image,null);
    }

    @Override
    public ResultData findNameById(Integer shopId) {
        String name = shopMapper.findNameById(shopId);
        if (name == null){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到店铺的名称！");
        }
        return new ResultData(Code.SELECT_OK,name,null);
    }

}

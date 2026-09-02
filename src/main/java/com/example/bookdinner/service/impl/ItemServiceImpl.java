package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.*;
import com.example.bookdinner.mapper.*;
import com.example.bookdinner.service.ItemService;
import com.example.bookdinner.util.BubbleSortUtil;
import com.example.bookdinner.util.ItemIdUtil;
import com.example.bookdinner.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

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
    private ShippingMapper shippingMapper;

    @Resource
    private AdviceMapper adviceMapper;

    @Override
    public ResultData addItem(Item item) {
        //查询空闲状态的骑手
        List<Rider> list = riderMapper.findFreeRider();
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"当前未有空闲骑手，请耐心等待");
        }else {
            //解决线程安全问题
            synchronized (this){
                Integer riderId = null;
                List<RiderNum> riderNumList = new ArrayList<>();
                if (list.size() == 1){
                    riderId = list.get(0).getId();
                }else {
                    for (Rider rider : list){
                        int count = itemMapper.findCountByRiderId(rider.getId());
                        RiderNum riderNum = new RiderNum(rider.getId(),count);
                        riderNumList.add(riderNum);
                    }
                    BubbleSortUtil.bubbleSort(riderNumList);
                    riderId = riderNumList.get(0).getRiderId();
                }
                //生成一个订单Id
                String itemId = ItemIdUtil.getItemId();
                item.setItemId(itemId);
                item.setRiderId(riderId);
                //将骑手的状态设置为繁忙
                int row = riderMapper.updateRiderStatus(riderId);
                //异步将骑手的状态设置为空闲（送达后）
                AsyncInvokeUtil.updateRiderToFree(riderId,itemId,item.getMinute(),riderMapper,itemMapper);
                if (row > 0){
                    //进行添加
                    int r = itemMapper.addItem(item);
                    if (r > 0){
                        //查询订单表的主键Id
                        int id = itemMapper.findItemByItemId(itemId).getId();
                        List<Cart> cartList = cartMapper.findAllCartByUserId(item.getUserId(),item.getShopId());
                        //修改月售量
                        for (Cart cart : cartList){
                            foodMapper.updateNumByFoodId(cart.getFoodId(),cart.getNum());
                        }
                        int backRow = 0;
                        for (Cart cart : cartList){
                            CartBack cartBack = new CartBack(id,cart.getUserId(),cart.getShopId(),cart.getFoodId(),cart.getNum());
                            //进行备份
                            int ro = cartMapper.addCartToCartBack(cartBack);
                            backRow = backRow + ro;
                        }
                        if (cartList.size() == backRow){
                            //备份成功,删除购物车中的数据
                            cartMapper.deleteFoodInCartByUserIdShopId(item.getUserId(),item.getShopId());
                        }
                        return new ResultData(Code.SAVE_OK,null,"添加成功！");
                    }else {
                        return new ResultData(Code.SAVE_ERR,null,"添加失败！");
                    }
                }else {
                    return new ResultData(Code.UPDATE_ERR,null,"更新失败！");
                }
            }
        }
    }

    @Override
    public ResultData findItemsByUserId(Integer userId) {
        //根据用户Id查询订单信息
        List<Item> itemList = itemMapper.findItemsByUserId(userId);
        if (itemList.size() != 0){
            for (Item item : itemList){
                //根据商店Id查询商店具体信息
                Shop shop = shopMapper.findShopById(item.getShopId());
                item.setShop(shop);
                if (item.getStatus() == 1){
                    item.setStatusString("派送中");
                }else if (item.getStatus() == 0){
                    item.setStatusString("已完成");
                }
                //根据订单Id查询购物车中的信息
                List<CartBack> cartBackList = cartMapper.findCartBackByItemId(item.getId());
                for (CartBack cartBack : cartBackList){
                    //查询商品信息
                    Food food = foodMapper.findFoodById(cartBack.getFoodId());
                    cartBack.setFood(food);
                }
                item.setCartList(cartBackList);
                System.out.println(item.getCartList());
            }
            return new ResultData(Code.SELECT_OK,itemList,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"您还没有订单");
    }

    @Override
    public ResultData findItemByUserIdBeLiked(Integer userId, String key) {
        String valueKey = "%" + key + "%";
        List<Shop> shopList = shopMapper.findShopByOnlyKey(valueKey);
        if (shopList.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关订单");
        }
        int[] array = new int[shopList.size()];
        for (int i = 0;i < array.length;i++){
            array[i] = shopList.get(i).getId();
        }
        //根据用户Id查询订单信息
        List<Item> itemList = itemMapper.findItemByUserIdShopId(userId,array);
        if (itemList.size() != 0){
            for (Item item : itemList){
                //根据商店Id查询商店具体信息
                Shop shop = shopMapper.findShopById(item.getShopId());
                item.setShop(shop);
                if (item.getStatus() == 1){
                    item.setStatusString("派送中");
                }else if (item.getStatus() == 0){
                    item.setStatusString("已完成");
                }
                //根据订单Id查询购物车中的信息
                List<CartBack> cartBackList = cartMapper.findCartBackByItemId(item.getId());
                for (CartBack cartBack : cartBackList){
                    //查询商品信息
                    Food food = foodMapper.findFoodById(cartBack.getFoodId());
                    cartBack.setFood(food);
                }
                item.setCartList(cartBackList);
            }
            return new ResultData(Code.SELECT_OK,itemList,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关订单");
    }

    @Override
    public ResultData findItemDetail(Integer itemId) {
        //根据订单信息查询订单详情
        Item item = itemMapper.findItemById(itemId);
        if (item != null){
            if (item.getStatus() == 0){
                item.setStatusString("已完成");
            }else if (item.getStatus() == 1){
                item.setStatusString("正在配送");
            }
            //根据订单主键Id查询购物车信息
            List<CartBack> cartBackList = cartMapper.findCartBackByItemId(item.getId());
            //创建一个容器
            List<CartAndPrice> cartAndPriceList = new ArrayList<>();
            //遍历集合，根据集合每个对象中的菜品Id查询相对应的菜品详情
            for (CartBack cartBack : cartBackList){
                int foodId = cartBack.getFoodId();
                Food food = foodMapper.findFoodById(foodId);
                cartBack.setFood(food);
                //处理价格
                float price = food.getPrice() * cartBack.getNum();
                int priceTimes = Math.round(price * 100);
                float priceEnd = (float) priceTimes / 100;
                CartAndPrice cartAndPrice = new CartAndPrice(cartBack,priceEnd);
                cartAndPriceList.add(cartAndPrice);
            }
            //根据地址Id查询地址信息
            Shipping shipping = shippingMapper.findAddressByIdNoNeedStatus(item.getAddressId());
            //根据骑手Id查询骑手信息
            Rider rider = riderMapper.findRiderById(item.getRiderId());
            //根据店铺Id查询店铺的信息
            Shop shop = shopMapper.findShopById(item.getShopId());
            ItemDetailData itemDetailData = new ItemDetailData(item,shop,cartAndPriceList,rider,shipping);
            return new ResultData(Code.SELECT_OK,itemDetailData,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的订单信息");
    }

    @Override
    public ResultData deleteItemById(Integer id,Integer userId) {
        int row = itemMapper.deleteItem(id);
        if (row > 0){
            List<Item> itemList = itemMapper.findItemsByUserId(userId);
            if (itemList.size() != 0){
                for (Item item : itemList){
                    //根据商店Id查询商店具体信息
                    Shop shop = shopMapper.findShopById(item.getShopId());
                    item.setShop(shop);
                    if (item.getStatus() == 1){
                        item.setStatusString("派送中");
                    }else if (item.getStatus() == 0){
                        item.setStatusString("已完成");
                    }
                    //根据订单Id查询购物车中的信息
                    List<CartBack> cartBackList = cartMapper.findCartBackByItemId(item.getId());
                    for (CartBack cartBack : cartBackList){
                        //查询商品信息
                        Food food = foodMapper.findFoodById(cartBack.getFoodId());
                        cartBack.setFood(food);
                    }
                    item.setCartList(cartBackList);
                }
                return new ResultData(Code.DELETE_OK,itemList,null);
            }
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关信息！");
        }
        return new ResultData(Code.DELETE_ERR,null,"删除失败！");
    }

    @Override
    public ResultData findAdviceNeedDataByItemId(Integer itemId) {
        Item item = itemMapper.findItemById(itemId);
        if (item != null){
            String createdString = item.getCreated();
            int first = createdString.indexOf("-");
            int second = createdString.lastIndexOf("-");
            int emptyKey = createdString.indexOf(" ");
            String month = createdString.substring(first+1,second);
            String date = createdString.substring(second + 1,emptyKey);
            Shop shop = shopMapper.findShopById(item.getShopId());
            Rider rider = riderMapper.findRiderById(item.getRiderId());
            String shopName = shop.getName();
            String riderName = rider.getName();
            String day = month + "月" + date + "日";
            String time = item.getArriveMinute();
            return new ResultData(Code.SELECT_OK,new AdivcePageData(shopName,riderName,day,time),null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的信息！");
    }

    @Override
    public ResultData updateItemStatus(Integer itemId,Integer userId) {
        //将订单的状态设置为已送达
        int row = itemMapper.updateItemStatusById(itemId);
        if (row == 0){
            return new ResultData(Code.UPDATE_ERR,null,"设置订单状态为已完成失败！");
        }
        //根据订单的Id查询订单的信息
        Item item1 = itemMapper.findItemById(itemId);
        if (item1 == null){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关订单！");
        }
        //根据骑手Id将骑手设置为空闲状态
        int riderRow = riderMapper.updateRiderToFree(item1.getRiderId());
        if (riderRow == 0){
            return new ResultData(Code.UPDATE_ERR,null,"骑手状态设置失败！");
        }
        //所有设置都完成后，查询订单的信息，返回给前端
        List<Item> itemList = itemMapper.findItemsByUserId(userId);
        if (itemList.size() != 0){
            for (Item item : itemList){
                //根据商店Id查询商店具体信息
                Shop shop = shopMapper.findShopById(item.getShopId());
                item.setShop(shop);
                if (item.getStatus() == 1){
                    item.setStatusString("派送中");
                }else if (item.getStatus() == 0){
                    item.setStatusString("已完成");
                }
                //根据订单Id查询购物车中的信息
                List<CartBack> cartBackList = cartMapper.findCartBackByItemId(item.getId());
                for (CartBack cartBack : cartBackList){
                    //查询商品信息
                    Food food = foodMapper.findFoodById(cartBack.getFoodId());
                    cartBack.setFood(food);
                }
                item.setCartList(cartBackList);
                System.out.println(item.getCartList());
            }
            return new ResultData(Code.SELECT_OK,itemList,null);
        }
        return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的订单信息！");
    }

    @Override
    public ResultData adminDeleteItemById(Integer id,Integer riderId) {
        int row = itemMapper.deleteItem(id);
        if (row <0){
            return new ResultData(Code.DELETE_ERR,null,"删除失败！");
        }
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

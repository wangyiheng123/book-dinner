package com.example.bookdinner;

import com.example.bookdinner.mapper.*;
import com.example.bookdinner.service.ItemService;
import com.example.bookdinner.service.KindService;
import com.example.bookdinner.service.ShippingService;
import com.example.bookdinner.service.WeekDataService;
import com.example.bookdinner.util.*;
import com.example.bookdinner.vo.AsyncInvokeUtil;
import com.example.bookdinner.vo.RiderNum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class BookDinnerApplicationTests {

    @Resource
    private ShippingMapper shippingMapper;

    @Resource
    private ShippingService shippingService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private KindService kindService;

    @Resource
    private RiderMapper riderMapper;

    @Resource
    private WeekDataService weekDataService;

    @Resource
    private AdviceMapper adviceMapper;

    @Test
    void contextLoads() {
        System.out.println(CheckCode.createCode());
    }

    @Test
    public void test(){
        System.out.println(Name.createName());
    }

    @Test
    public void test1(){
//        AsyncInvokeUtil.updateRiderToFree(6,22,riderMapper);
//        riderMapper.updateRiderToFree(6);
//        int[] array = new int[]{14,9,15,3,17,52,13,0,0};
//        BubbleSortUtil.bubbleSort(array);
//        System.out.println(Arrays.toString(array));
        List<RiderNum> riderNumList = new ArrayList<>();
        riderNumList.add(new RiderNum(1,6));
        riderNumList.add(new RiderNum(2,18));
        riderNumList.add(new RiderNum(3,8));
        riderNumList.add(new RiderNum(4,7));
        riderNumList.add(new RiderNum(5,12));
        riderNumList.add(new RiderNum(6,2));
        BubbleSortUtil.bubbleSort(riderNumList);
        System.out.println(riderNumList);
    }

}

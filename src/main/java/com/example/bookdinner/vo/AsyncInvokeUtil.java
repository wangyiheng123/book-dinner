package com.example.bookdinner.vo;

import com.example.bookdinner.mapper.ItemMapper;
import com.example.bookdinner.mapper.RiderMapper;

import java.util.Timer;
import java.util.TimerTask;

/*
执行异步调用的工具类
 */
public class AsyncInvokeUtil {

    public static void updateRiderToFree(Integer riderId,String itemId, Integer minute, RiderMapper riderMapper, ItemMapper itemMapper){
        //启用一个异步线程
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int row = riderMapper.updateRiderToFree(riderId);
                int r = itemMapper.updateItemStatus(itemId);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,minute * 60 * 1000);
    }

}

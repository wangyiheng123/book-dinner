package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.WeekData;
import com.example.bookdinner.mapper.WeekDataMapper;
import com.example.bookdinner.service.WeekDataService;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.CountArray;
import com.example.bookdinner.vo.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("weekDataService")
public class WeekDataServiceImpl implements WeekDataService {

    @Resource
    private WeekDataMapper weekDataMapper;

    @Override
    public ResultData findAllWeekData() {
        List<WeekData> list = weekDataMapper.findAllWeekData();
        Stream<WeekData> stream = list.stream();
        //按照菜系ID用Stream流进行分组
        Map<Integer,List<WeekData>> map = stream.collect(
                Collectors.groupingBy(WeekData::getKindId)
        );
        Class clazz = CountArray.class;
        Object o = null;
        try {
            o = clazz.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(map);
        for (int i = 0;i < map.size();i++){
            List<WeekData> mapData = map.get(i + 1);
            int[] array = new int[7];
            for (int j = 0;j < array.length;j++) {
                array[j] = mapData.get(j).getNum();
            }
            try {
                //利用反射对对象进行赋值
                Field field = clazz.getDeclaredField("array"+ (i +1));
                field.setAccessible(true);
                field.set(o,array);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CountArray countArray = (CountArray) o;
        return new ResultData(Code.SELECT_OK,countArray,null);
    }
}

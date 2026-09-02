package com.example.bookdinner.util;

import com.example.bookdinner.entity.Rider;
import com.example.bookdinner.vo.RiderNum;

import java.util.List;

/*
冒泡排序的工具类
 */
public class BubbleSortUtil {

//    public static void bubbleSort(int[] arr) {
//        int temp;
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//    }

    public static void bubbleSort(List<RiderNum> riderList) {
        RiderNum riderNum = null;
        for (int i = 0;i < riderList.size();i++){
            for (int j = 0;j < riderList.size() - 1 - i;j++){
                if (riderList.get(j).getNum() > riderList.get(j + 1).getNum()){
                    riderNum = riderList.get(j);
                    riderList.remove(j);
                    riderList.add(j+1,riderNum);
                }
            }
        }
    }

}

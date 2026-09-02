package com.example.bookdinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
评价页面所需的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdivcePageData {

    private String shopName;   //店铺名称

    private String riderName;  //骑手名称

    private String day;   //送达日期

    private String time;  //送达的具体时间

}

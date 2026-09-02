package com.example.bookdinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
用于接收前端传递过来的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemIdAndAdviceData {

    private Integer userId; //用户Id

    private Integer itemId;  //订单Id

    private Integer shopStar; //店铺星级

    private Integer riderStar; //骑手星级

    private String comment; //评论内容

}

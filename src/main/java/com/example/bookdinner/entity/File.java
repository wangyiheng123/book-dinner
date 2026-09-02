package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
评论与图片映射的实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

    private Integer id; //主键Id

    private Integer itemId; //订单id

    private String image;  //图片路径

}

package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
订单是否评论过
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviceStatus {

    private Integer id;

    private Integer userId;

    private Integer ItemId;

}

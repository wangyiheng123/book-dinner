package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
营业执照实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopImage {

    private Integer id;

    private Integer shopId;

    private String image;

}

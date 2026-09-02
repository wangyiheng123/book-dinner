package com.example.bookdinner.vo;

import com.example.bookdinner.entity.Food;
import com.example.bookdinner.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopNeedData {

    private Shop shop;

    private String image;

    private List<Food> foodList;

    private String city;
}

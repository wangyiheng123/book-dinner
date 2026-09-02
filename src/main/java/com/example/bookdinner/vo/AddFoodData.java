package com.example.bookdinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFoodData {

    private Integer userId;

    private Integer shopId;

    private String name;

    private Float price;

    private Integer kindId;

    private String chargeMixture;

    private String mouthFeel;

    private String temperature;

    private String meatAndVegetables;

    private String method;

    private String minute;

    private String weight;


}

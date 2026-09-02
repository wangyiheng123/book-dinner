package com.example.bookdinner.vo;

import com.example.bookdinner.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodAndKindName {

    private Food food;

    private String kindName;

}

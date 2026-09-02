package com.example.bookdinner.vo;

import com.example.bookdinner.entity.CartBack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartAndPrice {

    private CartBack cartBack;

    private Float price;

}

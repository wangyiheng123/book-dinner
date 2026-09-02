package com.example.bookdinner.vo;

import com.example.bookdinner.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailData {

    private Item item;

    private Shop shop;

    private List<CartAndPrice> cartAndPriceList;

    private Rider rider;

    private Shipping shipping;

}

package com.example.bookdinner.vo;

import com.example.bookdinner.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemListAndStarNum {

    private Item item;

    private List<Integer> starList;

}

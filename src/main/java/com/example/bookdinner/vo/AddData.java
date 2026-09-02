package com.example.bookdinner.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddData {

    private Integer cityId;

    private String name;

    private String location;

    private String sign;

    private Integer userId;

}

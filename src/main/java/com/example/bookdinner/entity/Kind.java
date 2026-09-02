package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kind {

    private Integer id;  //种类ID

    private String kindName;  //种类名称

    private Integer num;  //种类数量

}

package com.example.bookdinner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rider {

    private Integer id; //骑手的Id

    private String name; //骑手的姓名

    private String age; //骑手的年龄

    private String phone; //骑手的手机号

    private Integer status; //骑手的状态

}

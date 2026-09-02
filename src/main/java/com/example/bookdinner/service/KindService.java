package com.example.bookdinner.service;

import com.example.bookdinner.vo.ResultData;

public interface KindService {

    /*
    查询所有种类
     */
    ResultData findAllKind();

    /*
    查询所有种类的Id和名称
     */
    ResultData findAllKindIdAndName();

}

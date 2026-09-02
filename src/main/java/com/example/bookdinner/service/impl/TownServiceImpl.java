package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.Town;
import com.example.bookdinner.mapper.TownMapper;
import com.example.bookdinner.service.TownService;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("townService")
public class TownServiceImpl implements TownService {

    @Resource
    private TownMapper townMapper;

    @Override
    public ResultData findAllTown() {
        List<Town> townList = townMapper.findAllTown();
        if (townList.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查询到相关的城市信息！");
        }
        return new ResultData(Code.SELECT_OK,townList,null);
    }
}

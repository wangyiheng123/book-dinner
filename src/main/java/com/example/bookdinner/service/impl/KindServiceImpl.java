package com.example.bookdinner.service.impl;

import com.example.bookdinner.entity.Kind;
import com.example.bookdinner.mapper.KindMapper;
import com.example.bookdinner.service.KindService;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("kindService")
public class KindServiceImpl implements KindService {

    @Resource
    private KindMapper kindMapper;

    @Override
    public ResultData findAllKind() {
        List<Kind> list = kindMapper.findAllKind();
        int[] kindArray = new int[8];
        for (int i = 0;i < list.size();i++){
            kindArray[i] = list.get(i).getNum();
        }
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到！");
        }
        return new ResultData(Code.SELECT_OK,kindArray,null);
    }

    @Override
    public ResultData findAllKindIdAndName() {
        List<Kind> list = kindMapper.findAllKind();
        if (list.size() == 0){
            return new ResultData(Code.SELECT_NOTFOUND,null,"没有查到！");
        }
        return new ResultData(Code.SELECT_OK,list,null);
    }
}

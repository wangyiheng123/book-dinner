package com.example.bookdinner.controller;

import com.example.bookdinner.service.FileService;
import com.example.bookdinner.vo.ResultData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/file/upload")
    public ResultData fileUpload(MultipartFile file,Integer itemId){
        return fileService.fileUpload(file,itemId);
    }

    @PostMapping("/file/imageUpload")
    public ResultData imageUpload(MultipartFile file,Integer userId){
        return fileService.imageUpload(file, userId);
    }

    @PostMapping("/file/imageUpload1")
    public ResultData imageUpload1(MultipartFile file,Integer userId){
        return fileService.imageUpload1(file, userId);
    }

    @PostMapping("/file/uploadFoodImage")
    public ResultData uploadFoodImage(MultipartFile file,Integer userId,Integer shopId){
        return fileService.uploadFoodImage(file, userId, shopId);
    }

}

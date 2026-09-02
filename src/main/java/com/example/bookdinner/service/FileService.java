package com.example.bookdinner.service;

import com.example.bookdinner.vo.ResultData;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /*
    上传文件
     */
    ResultData fileUpload(MultipartFile file,Integer itemId);

    /*
    上传店铺图片
     */
    ResultData imageUpload(MultipartFile file,Integer userId);

    /*
    上传店铺营业执照
     */
    ResultData imageUpload1(MultipartFile file,Integer userId);

    /*
    上传店铺中菜品的图片
     */
    ResultData uploadFoodImage(MultipartFile file,Integer userId,Integer shopId);
}

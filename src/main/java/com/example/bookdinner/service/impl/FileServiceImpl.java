package com.example.bookdinner.service.impl;

import com.example.bookdinner.mapper.FileMapper;
import com.example.bookdinner.service.FileService;
import com.example.bookdinner.vo.Code;
import com.example.bookdinner.vo.ResultData;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultData fileUpload(MultipartFile file, Integer itemId) {
        if (file != null){
            //获取文件的名字
            String originalFileName = file.getOriginalFilename();
            //获取文件的后缀名
            String subString = originalFileName.substring(originalFileName.lastIndexOf("."));
            //用UUID随机生成文件名
            String fileName = UUID.randomUUID().toString() + subString;
            //保存文件
            try {
                file.transferTo(new File("D:/arithmetic/book-dinner/src/main/resources/static/img/"+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String path = "/img/"+fileName;
            //将图片的路径存储入数据库中
            fileMapper.savePicture(itemId,path);
            return new ResultData(Code.FILE_UPLOAD_SUCCESS,path,"文件上传成功！");
        }
        return new ResultData(Code.FILE_UPLOAD_FAIL,null,"没有选择文件");
    }

    @Override
    public ResultData imageUpload(MultipartFile file, Integer userId) {
        if (file == null){
            return new ResultData(Code.FILE_UPLOAD_FAIL,null,"图片上传失败！");
        }
        String originalFileName = file.getOriginalFilename();
        //获取文件的后缀名
        String subString = originalFileName.substring(originalFileName.lastIndexOf("."));
        //用UUID随机生成文件名
        String fileName = UUID.randomUUID().toString() + subString;
        //保存文件
        try {
            file.transferTo(new File("D:/arithmetic/book-dinner/src/main/resources/static/img/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "/img/"+fileName;
        //以用户Id为key，将图片路径存入redis中
        stringRedisTemplate.opsForValue().set("adminUser:image:" + userId,path,30, TimeUnit.MINUTES);
        return new ResultData(Code.FILE_UPLOAD_SUCCESS,path,null);
    }

    @Override
    public ResultData imageUpload1(MultipartFile file, Integer userId) {
        if (file == null){
            return new ResultData(Code.FILE_UPLOAD_FAIL,null,"图片上传失败！");
        }
        String originalFileName = file.getOriginalFilename();
        //获取文件的后缀名
        String subString = originalFileName.substring(originalFileName.lastIndexOf("."));
        //用UUID随机生成文件名
        String fileName = UUID.randomUUID().toString() + subString;
        //保存文件
        try {
            file.transferTo(new File("D:/arithmetic/book-dinner/src/main/resources/static/img/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "/img/"+fileName;
        //以用户Id为key，将图片路径存入redis中
        stringRedisTemplate.opsForValue().set("adminUser:image1:" + userId,path,30, TimeUnit.MINUTES);
        return new ResultData(Code.FILE_UPLOAD_SUCCESS,path,null);
    }

    @Override
    public ResultData uploadFoodImage(MultipartFile file, Integer userId, Integer shopId) {
        if (file == null){
            return new ResultData(Code.FILE_UPLOAD_FAIL,null,"图片上传失败！");
        }
        String originalFileName = file.getOriginalFilename();
        //获取文件的后缀名
        String subString = originalFileName.substring(originalFileName.lastIndexOf("."));
        //用UUID随机生成文件名
        String fileName = UUID.randomUUID().toString() + subString;
        //保存文件
        try {
            file.transferTo(new File("D:/arithmetic/book-dinner/src/main/resources/static/img/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "/img/"+fileName;
        stringRedisTemplate.opsForValue().set("adminUser:"+userId+":"+shopId,path,30, TimeUnit.MINUTES);
        return new ResultData(Code.FILE_UPLOAD_SUCCESS,path,null);
    }
}

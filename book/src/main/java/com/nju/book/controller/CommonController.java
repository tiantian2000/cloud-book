package com.nju.book.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${web.upload-path}")
    private String path;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public String upload(MultipartFile file){
        String originFileName = file.getOriginalFilename();
        //重新命名文件名
        String newFileName = UUID.randomUUID() + originFileName.substring(originFileName.lastIndexOf('.'));
        try {
            //创建输出文件流对象:目录+文件名
            FileOutputStream fos = new FileOutputStream(path+newFileName);
            //复制文件，从源文件流到目标文件流
            FileCopyUtils.copy(file.getInputStream(),fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFileName;
    }
}

package com.neuedu.controller;

import com.neuedu.Service.IProductService;
import com.neuedu.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {
    @Autowired
    IProductService iProductService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> upload(MultipartFile upload) {
        return iProductService.upload(upload);
    }

   /* @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("upload") MultipartFile upload) {

        //重新生成文件名 UUID+扩展名(jsp)
        if (upload != null) {
            //step1:获取原文件扩展名
            String originFilename = upload.getOriginalFilename();

            if (originFilename != null && !originFilename.equals("")) {
                int index = originFilename.lastIndexOf('.');
                String extendname = originFilename.substring(index); //[)

                // step2:生成一个文件名
                String uuid = UUID.randomUUID().toString();
                String newFilename = uuid + extendname;

                //step3:
                String filePath = "D:\\ftpfilter";
                File file = new File(filePath, newFilename);
                try {
                    upload.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "upload";
    }*/
}

package com.wzh.myshop.web.admin.controller;


import com.wzh.myshop.commons.utils.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wzh
 * @date 2019/9/27 - 16:00
 */
@Controller
public class UploadController {
    private static final String UPLOAD_PATH = "static/upload/";

    /**
     * 上传
     * @param file  dropZone上传
     * @param editorFile wangEditor上传
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("upload")
    public Map<String,Object> uoload(MultipartFile file,MultipartFile editorFile, HttpServletRequest request){
        MultipartFile myFile = file == null?editorFile:file;
        // 获取上传的原始文件名
        String fileName = myFile.getOriginalFilename();
        // 设置文件上传路径
        String filePath = null;
        try {
            filePath = ResourceUtils.getURL("classpath:").getPath()+UPLOAD_PATH;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        // 判断并创建上传用的文件夹
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.mkdir();
        }
        newFile = new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            // 写入文件
            myFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> data = new HashMap<>();
        //文件URI路径
        String contentPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/upload/"+newFile.getName();
        if(file == null){
            data.put("errno",0);
            data.put("data",new String[]{UrlUtils.getBaseUrl(request)+contentPath});
        }else{
            data.put("filename",contentPath);
        }
        return data;
    }
}

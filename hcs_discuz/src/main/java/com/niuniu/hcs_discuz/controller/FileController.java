package com.niuniu.hcs_discuz.controller;

import com.niuniu.hcs_discuz.service.IFileService;
import com.niuniu.hcs_discuz.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author niuniu
 * @date 2022/3/23
 */
@RequestMapping({"/file"})
@RestController
public class FileController {

    @Autowired
    IFileService fileService;

    /**
     * 上传文件
     */
    @RequestMapping({"/upload"})
    public JsonResult fileUpload(@RequestParam("file") MultipartFile file) {
        JsonResult jsonResult = new JsonResult();
        if (file.isEmpty()) {
            jsonResult.setFail("文件为空！");
        }
        String filePath = fileService.saveUploadFile(file);
        if (null != filePath) {
            jsonResult.setSuccess("上传成功！");
            jsonResult.addDatas("filePath", filePath);
        } else {
            jsonResult.setFail("上传失败！");
        }
        return jsonResult;
    }

    /**
     * 测试
     */
    @RequestMapping({"/test"})
    public void testUpload(String filename) throws FileNotFoundException {

        InputStream in = new FileInputStream(new File("D:/var/upload" + filename));// 将该文件加入到输入流之中

    }

}

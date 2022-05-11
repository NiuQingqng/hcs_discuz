package com.niuniu.hcs_discuz.controller;

import com.niuniu.hcs_discuz.model.Image;
import com.niuniu.hcs_discuz.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    IImageService imageService;

    @RequestMapping("findall")
    public List<Image> findAll() {
        return imageService.findAll();
    }

    @RequestMapping("findone")
    public Image findOneImageById(Integer imageId) {
        return imageService.findOneImageById(imageId);
    }

    @RequestMapping("findby")
    public List<Image> findImageByCondition(Image image) {
        return imageService.findImageByCondition(image);
    }

    @RequestMapping("add")
    public Integer addImage(Image image) {
        return imageService.addImage(image);
    }

    @RequestMapping("del")
    public Integer delImage(Integer imageId) {
        return imageService.delImage(imageId);
    }

}

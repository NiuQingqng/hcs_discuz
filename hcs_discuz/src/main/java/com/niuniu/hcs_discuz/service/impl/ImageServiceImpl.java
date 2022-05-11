package com.niuniu.hcs_discuz.service.impl;

import com.niuniu.hcs_discuz.dao.IImageDao;
import com.niuniu.hcs_discuz.model.Image;
import com.niuniu.hcs_discuz.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    IImageDao iImageDao;

    @Override
    public List<Image> findAll() {
        return iImageDao.findAll();
    }

    @Override
    public Image findOneImageById(Integer imageId) {
        return iImageDao.findOneImageById(imageId);
    }

    @Override
    public List<Image> findImageByCondition(Image image) {
        return iImageDao.findImageByCondition(image);
    }

    @Override
    public Integer addImage(Image image) {
        return iImageDao.addImage(image);
    }

    @Override
    public Integer delImage(Integer imageId) {
        return iImageDao.delImage(imageId);
    }

}

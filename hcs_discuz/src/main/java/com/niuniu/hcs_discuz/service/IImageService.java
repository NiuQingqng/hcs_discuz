package com.niuniu.hcs_discuz.service;

import com.niuniu.hcs_discuz.model.Image;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
public interface IImageService {
    List<Image> findAll();

    Image findOneImageById(Integer imageId);

    /**
     * 使用条件查询图片
     *
     * @author
     * @date
     */
    List<Image> findImageByCondition(Image image);

    /**
     * 添加图片
     *
     * @author
     * @date
     */
    Integer addImage(Image image);

    /**
     * 假删除图片
     *
     * @author
     * @date
     */
    Integer delImage(Integer imageId);
}

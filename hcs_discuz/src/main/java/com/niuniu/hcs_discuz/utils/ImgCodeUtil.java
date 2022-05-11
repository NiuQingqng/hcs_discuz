package com.niuniu.hcs_discuz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @author niuniu
 * @date 2022/3/16
 */
@Component
public class ImgCodeUtil {

    @Autowired
    RedisCache redisCache;

    public BufferedImage createImgCode(String imgCodeId) {
        //创建验证码对象
        ImgCode imgCode = new ImgCode();
        //验证码存储到Redis
        redisCache.set(imgCodeId, imgCode.getText().toLowerCase(), 60 * 5);
        //返回图片和验证码
        return imgCode.getImage();
    }

    public boolean checkImgCode(String imgCodeId, String imgCodeText) {
        imgCodeText = imgCodeText.toLowerCase();
        String imgCodeTextCache = (String) redisCache.get(imgCodeId);
        if (imgCodeText.equals(imgCodeTextCache)) return true;
        else return false;
    }
}

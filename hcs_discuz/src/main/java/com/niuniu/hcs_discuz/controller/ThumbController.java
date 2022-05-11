package com.niuniu.hcs_discuz.controller;

import com.niuniu.hcs_discuz.model.Thumb;
import com.niuniu.hcs_discuz.service.IThumbService;
import com.niuniu.hcs_discuz.utils.JsonResult;
import com.niuniu.hcs_discuz.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/14
 */
@ResponseBody
@Controller
@RequestMapping("thumb")
public class ThumbController {

    @Autowired
    IThumbService thumbService;

    @Autowired
    TokenUtil tokenUtil;

    @RequestMapping("findone")
    public Thumb findThumbById(Integer thumbId) {
        return thumbService.findThumbById(thumbId);
    }

    @RequestMapping("findby")
    public List<Thumb> findThumbByCondition(Thumb thumb) {
        return thumbService.findThumbByCondition(thumb);
    }

    @RequestMapping("add")
    public JsonResult addThumb(@RequestBody Thumb thumb, @RequestHeader(value = "token") String token) {
        thumb.setFromUserId(tokenUtil.getCacheUser(token).getUserId());
        JsonResult result = new JsonResult();
        if (thumbService.addThumb(thumb)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    @RequestMapping("del")
    public Integer delThumb(Integer thumbId) {
        return thumbService.delThumb(thumbId);
    }

}

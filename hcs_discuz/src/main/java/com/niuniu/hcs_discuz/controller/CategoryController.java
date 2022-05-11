package com.niuniu.hcs_discuz.controller;

import com.niuniu.hcs_discuz.model.Category;
import com.niuniu.hcs_discuz.model.User;
import com.niuniu.hcs_discuz.service.ICategoryService;
import com.niuniu.hcs_discuz.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/2
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    //查询分类列表
    @RequestMapping("/findall")
    public JsonResult findAll() {
        JsonResult result = new JsonResult();
        result.setSuccess();
        List<Category> categories = categoryService.findAll();
        result.addDatas("categoryList", categories);
        return result;
    }

    //删除分类
    @RequestMapping("/delcategory")
    public JsonResult delCategory(Integer categoryId) {
        JsonResult result = new JsonResult();
        if (categoryService.delCategory(categoryId)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    //修改分类
    @RequestMapping("/updatecategory")
    public JsonResult updateCategory(@RequestBody Category category) {
        JsonResult result = new JsonResult();
        if (categoryService.updateCategory(category)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

    //新增分类信息
    @RequestMapping("/addcategory")
    public JsonResult addCategory(@RequestBody Category category) {
        JsonResult result = new JsonResult();
        if (categoryService.addCategory(category)) {
            result.setSuccess();
        } else {
            result.setSysErr();
        }
        return result;
    }

}

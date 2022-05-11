package com.niuniu.hcs_discuz.service;

import com.niuniu.hcs_discuz.model.Category;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/2
 */
public interface ICategoryService {

    List<Category> findAll();

    Category findOneCategoryById(Integer categoryId);

    /**
     * 添加分类信息
     *
     * @author
     * @date
     */
    Boolean addCategory(Category category);

    /**
     * 编辑分类信息
     *
     * @author
     * @date
     */
    Boolean updateCategory(Category category);

    /**
     * 分类下帖子数量变化时更新数量
     *
     * @author
     * @date
     */
    Boolean updateCount(Integer categoryId, Integer option);

    /**
     * 假删除分类
     *
     * @author
     * @date
     */
    Boolean delCategory(Integer categoryId);

}

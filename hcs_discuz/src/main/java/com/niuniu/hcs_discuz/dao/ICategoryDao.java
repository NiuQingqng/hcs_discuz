package com.niuniu.hcs_discuz.dao;

import com.niuniu.hcs_discuz.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/1
 */
@Repository
public interface ICategoryDao {

    List<Category> findAll();

    Category findOneCategoryById(Integer categoryId);

    /**
     * 添加分类信息
     *
     * @author
     * @date
     */
    Integer addCategory(Category category);

    /**
     * 编辑分类信息
     *
     * @author
     * @date
     */
    Integer updateCategory(Category category);

    /**
     * 分类下帖子数量变化时更新数量
     *
     * @author
     * @date
     */
    Integer updateCount(Category category);

    /**
     * 假删除分类
     *
     * @author
     * @date
     */
    Integer delCategory(Integer categoryId);

}

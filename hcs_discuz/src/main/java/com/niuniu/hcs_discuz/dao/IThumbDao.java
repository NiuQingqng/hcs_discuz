package com.niuniu.hcs_discuz.dao;

import com.niuniu.hcs_discuz.model.Thumb;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/1
 */
@Repository
public interface IThumbDao {

    /**
     * 使用id查询赞
     *
     * @author
     * @date
     */
    Thumb findThumbById(Integer thumbId);

    /**
     * 使用条件查询赞
     *
     * @author
     * @date
     */
    List<Thumb> findThumbByCondition(Thumb thumb);

    /**
     * 添加赞
     *
     * @author
     * @date
     */
    Integer addThumb(Thumb thumb);

    /**
     * 假删除点赞
     *
     * @author
     * @date
     */
    Integer delThumb(Integer thumbId);

}

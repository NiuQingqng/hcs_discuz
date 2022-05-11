package com.niuniu.hcs_discuz.dao;

import com.niuniu.hcs_discuz.model.Advise;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/3/1
 */
@Repository
public interface IAdviseDao {

    List<Advise> findAll();

    List<Advise> findAllMessage();

    List<Advise> findAdviseByCategory(Integer category);

    Integer addAdvise(Advise advise);

    Integer handleAdvise(Advise advise);

    Integer deleteAdvise(Integer adviseId);

}

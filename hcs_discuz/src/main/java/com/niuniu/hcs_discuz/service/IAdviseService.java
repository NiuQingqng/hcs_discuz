package com.niuniu.hcs_discuz.service;

import com.niuniu.hcs_discuz.model.Advise;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/4/24
 */
public interface IAdviseService {

    List<Advise> findAll();

    List<Advise> findAllMessage();

    List<Advise> findAdviseByCategory(Integer category);

    Boolean addAdvise(Advise advise);

    Boolean handleAdvise(Advise advise);

    Boolean deleteAdvise(Integer adviseId);

}

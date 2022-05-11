package com.niuniu.hcs_discuz.service.impl;

import com.niuniu.hcs_discuz.dao.IAdviseDao;
import com.niuniu.hcs_discuz.model.Advise;
import com.niuniu.hcs_discuz.service.IAdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author niuniu
 * @date 2022/4/24
 */
@Service
public class AdviseServiceImpl implements IAdviseService {

    @Autowired
    private IAdviseDao adviseDao;

    @Override
    public List<Advise> findAll() {
        return adviseDao.findAll();
    }

    @Override
    public List<Advise> findAllMessage() {
        return adviseDao.findAllMessage();
    }

    @Override
    public List<Advise> findAdviseByCategory(Integer category) {
        return adviseDao.findAdviseByCategory(category);
    }

    @Override
    public Boolean addAdvise(Advise advise) {
        return adviseDao.addAdvise(advise) > 0 ? true : false;
    }

    @Override
    public Boolean handleAdvise(Advise advise) {
        return adviseDao.handleAdvise(advise) > 0 ? true : false;
    }

    @Override
    public Boolean deleteAdvise(Integer adviseId) {
        return adviseDao.deleteAdvise(adviseId) > 0 ? true : false;
    }

}

package com.gg.compJob;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompJobServiceImpl implements CompJobService {
    @Autowired
    CompJobDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public CompJobDTO getCompJob(int comp_id) {
        return dao.getCompJob(session, comp_id);
    }

    @Override
    public List<CompJobDTO> getCompJobList() {
        return dao.getCompJobList(session);
    }
}

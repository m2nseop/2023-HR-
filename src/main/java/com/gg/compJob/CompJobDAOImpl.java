package com.gg.compJob;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompJobDAOImpl implements CompJobDAO {
    @Override
    public CompJobDTO getCompJob(SqlSessionTemplate session, int comp_id) {
        return session.selectOne("UserMapper.getCompJob", comp_id);
    }

    @Override
    public List<CompJobDTO> getCompJobList(SqlSessionTemplate session) {
        // TODO Auto-generated method stub
        return session.selectList("UserMapper.getCompJobList");
    }
}

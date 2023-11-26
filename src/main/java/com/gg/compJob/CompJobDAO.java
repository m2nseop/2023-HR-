package com.gg.compJob;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public interface CompJobDAO {
    public abstract CompJobDTO getCompJob(SqlSessionTemplate session, int comp_id);

    public abstract List<CompJobDTO> getCompJobList(SqlSessionTemplate session);
}

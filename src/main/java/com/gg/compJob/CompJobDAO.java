package com.gg.compJob;

import org.mybatis.spring.SqlSessionTemplate;

public interface CompJobDAO {
    public abstract CompJobDTO getCompJob(SqlSessionTemplate session, int comp_id);
}

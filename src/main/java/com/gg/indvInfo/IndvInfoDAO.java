package com.gg.indvInfo;

import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface IndvInfoDAO {
    // public abstract List<IndvInfoDTO> searchWithKeywords(SqlSessionTemplate
    // session, List<String> keywords);
    public abstract List<IndvInfoDTO> searchWithKeywords(SqlSessionTemplate session, String keywords);
}

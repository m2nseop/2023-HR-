package com.gg.indvInfo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IndvInfoDAOImpl implements IndvInfoDAO {
    @Override
    public List<IndvInfoDTO> searchWithKeywords(SqlSessionTemplate session, String keywords) {
        return session.selectList("UserMapper.searchWithKeywords", keywords);
    }
}

package com.gg.indvInfo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndvInfoServiceImpl implements IndvInfoService {

    @Autowired
    IndvInfoDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<IndvInfoDTO> searchWithKeywords(String keywords) {
        return dao.searchWithKeywords(session, keywords);
    }
}

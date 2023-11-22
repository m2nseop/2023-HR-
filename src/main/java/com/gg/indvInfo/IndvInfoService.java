package com.gg.indvInfo;

import java.util.List;

public interface IndvInfoService {
    public abstract List<IndvInfoDTO> searchWithKeywords(String keywords);
}

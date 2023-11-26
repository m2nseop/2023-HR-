package com.gg.compJob;

import java.util.List;

public interface CompJobService {
    public abstract CompJobDTO getCompJob(int comp_id);

    public abstract List<CompJobDTO> getCompJobList();
}

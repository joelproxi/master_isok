package com.uds.master_isok.uv;

import org.springframework.data.domain.Page;

public interface UeService {
    Page<UeResponse> getAllUes(int page, int size, String search, String... sort);
    UeResponse getUeById(Long id);
    Long createUe(UeRequest dto);
    Long updateUe(Long id, UeRequest dto);
    void deleteUe(Long id);
    Page<UeResponse> getUesByTeacherId(Long teacherId, int page, int size, String search, String... sort);
}

package com.newedo.projects.binary.dataps.service.itf;

import com.newedo.projects.binary.dataps.model.MetadataVO;

import java.util.List;

/**
 * 元数据业务类
 */
public interface IMetadataService {
    MetadataVO create(MetadataVO vo);
    MetadataVO delete(MetadataVO vo);
    MetadataVO findRoot();
    List<MetadataVO> findAll();
    MetadataVO findById(String id);
    MetadataVO update(MetadataVO vo);
}

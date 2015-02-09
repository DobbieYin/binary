package com.newedo.projects.binary.dataps.service.impl;

import com.newedo.projects.binary.dataps.dao.impl.MetadataDAOImpl;
import com.newedo.projects.binary.dataps.dao.itf.IMetadataDAO;
import com.newedo.projects.binary.dataps.model.MetadataVO;
import com.newedo.projects.binary.dataps.repository.MetadataRepository;
import com.newedo.projects.binary.dataps.service.itf.IMetadataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dobbie on 15-2-2.
 */
@Service
public class MetadataServiceImpl implements IMetadataService {
    private IMetadataDAO dao = new MetadataDAOImpl();
    @Override
    public MetadataVO create(MetadataVO vo) {
        return dao.create(vo);
    }

    @Override
    public MetadataVO delete(MetadataVO vo) {
        return null;
    }

    @Override
    public MetadataVO findRoot() {
        return null;
    }

    @Override
    public List<MetadataVO> findAll() {
        return null;
    }

    @Override
    public MetadataVO findById(String id) {
        return null;
    }

    @Override
    public MetadataVO update(MetadataVO vo) {
        return null;
    }
}

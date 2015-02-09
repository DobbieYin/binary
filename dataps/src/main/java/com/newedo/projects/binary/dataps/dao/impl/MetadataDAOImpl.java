package com.newedo.projects.binary.dataps.dao.impl;

import com.newedo.projects.binary.dataps.dao.itf.IMetadataDAO;
import com.newedo.projects.binary.dataps.model.MetadataVO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by dobbie on 15-2-2.
 */
public class MetadataDAOImpl implements IMetadataDAO {
    @Override
    public MetadataVO create(MetadataVO vo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(vo);
        em.getTransaction().commit();
        emf.close();
        return vo;
    }
}

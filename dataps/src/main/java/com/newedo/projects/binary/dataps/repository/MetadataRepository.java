package com.newedo.projects.binary.dataps.repository;

import com.newedo.projects.binary.dataps.model.MetadataVO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dobbie on 15-2-2.
 */
public interface MetadataRepository extends JpaRepository<MetadataVO,String> {
}

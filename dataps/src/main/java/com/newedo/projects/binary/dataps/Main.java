package com.newedo.projects.binary.dataps;

import com.newedo.projects.binary.dataps.model.MetadataVO;
import com.newedo.projects.binary.dataps.service.impl.MetadataServiceImpl;

import java.util.UUID;

/**
 * Created by dobbie on 15-2-2.
 */
public class Main {
    public static void main(String[] args){
        MetadataVO metadataVO = new MetadataVO();
        metadataVO.setCode("编码");
        metadataVO.setName("名称");
        new MetadataServiceImpl().create(metadataVO);

    }
}
package com.team.application.service;

import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.domain.entity.MaterialEntity;

import java.io.InputStream;

public interface MaterialService {

    MaterialVO upload(FileVO fileVO, String targetKey) throws Exception;

    InputStream getStreamByUid(String uid) throws Exception;

    InputStream getStreamBy(MaterialEntity me) throws Exception;

}

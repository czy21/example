package com.team.application.service;

import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;

public interface MaterialService {

    MaterialVO upload(FileVO fileVO, String targetKey) throws Exception;
}

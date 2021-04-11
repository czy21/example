package com.team.application.service.impl;

import com.team.application.model.dto.MaterialDTO;
import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.application.util.MaterialUtil;
import com.team.application.util.MultipartFileUtil;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.entity.MaterialTargetEntity;
import com.team.domain.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public MaterialVO upload(FileVO fileVO, String targetKey) throws IOException {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setName(fileVO.getFile().getOriginalFilename());
        materialDTO.setPath(MultipartFileUtil.generateFileName(fileVO.getFile()));
        materialDTO.setMediaType(fileVO.getFile().getContentType());
        return singleFileStore(materialDTO, fileVO.getFile().getInputStream(), targetKey);
    }


    private MaterialVO singleFileStore(MaterialDTO dto, InputStream inputStream, String targetKey) throws IOException {
        Assert.notNull(targetKey, "file_target_key not null");
        MaterialTargetEntity fileTarget = Optional.ofNullable(materialMapper.selectMaterialTargetByKey(targetKey)).orElse(new MaterialTargetEntity());
        Assert.notNull(fileTarget.getKey(), "file.targetKey_no_exist");

        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(UUID.randomUUID().toString());
        materialEntity.setName(dto.getName());
        materialEntity.setKind(dto.getMediaType());
        materialEntity.setMaterialTarget(fileTarget);
        switch (fileTarget.getTargetKind()) {
            case OSS:
                materialEntity.setPath(dto.getPath());
//                AliBasicHelper.OSS_SERVICE.obtain(materialEntity.getMaterialTarget().getRootPath()).uploadFile(materialEntity.getPath(), inputStream);
                break;
            case LOCAL:
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                materialEntity.setPath(MaterialUtil.ofPath(currentTime, dto.getPath()));
                File desc = MaterialUtil.getFile(dto.getPath(), fileTarget.getRootPath(), currentTime);
                FileOutputStream outputStream = new FileOutputStream(desc);
                inputStream.transferTo(outputStream);
                inputStream.close();
                outputStream.close();
                break;
            default:
                return null;
        }
        materialMapper.insertMaterial(materialEntity);
        return new MaterialVO(materialEntity.getName(), MaterialUtil.ofPath(materialEntity.getMaterialTarget().getRootUrl(), materialEntity.getPath()), materialEntity.getId());
    }

}

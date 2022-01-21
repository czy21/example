package com.team.application.service.impl;

import com.team.application.model.dto.MaterialDTO;
import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.application.util.MultipartFileUtil;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.entity.MaterialTargetEntity;
import com.team.domain.mapper.MaterialMapper;
import com.team.infrastructure.oss.OSSClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialMapper materialMapper;
    @Autowired
    OSSClient ossClient;

    @Transactional
    @Override
    public MaterialVO upload(FileVO fileVO, String targetKey) throws Exception {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setName(fileVO.getFile().getOriginalFilename());
        materialDTO.setPath(MultipartFileUtil.generateFileName(fileVO.getFile()));
        materialDTO.setMediaType(fileVO.getFile().getContentType());
        return singleFileStore(materialDTO, fileVO.getFile().getInputStream(), targetKey);
    }


    private MaterialVO singleFileStore(MaterialDTO dto, InputStream fileStream, String targetKey) throws Exception {
        Assert.notNull(targetKey, "file_target_key not null");
        MaterialTargetEntity fileTarget = Optional.ofNullable(materialMapper.selectMaterialTargetByKey(targetKey)).orElse(new MaterialTargetEntity());
        Assert.notNull(fileTarget.getKey(), "file.targetKey_no_exist");

        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(UUID.randomUUID().toString());
        materialEntity.setName(dto.getName());
        materialEntity.setKind(dto.getMediaType());
        materialEntity.setMaterialTarget(fileTarget);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = FilenameUtils.separatorsToUnix(Path.of(currentTime, dto.getPath()).toString());
        materialEntity.setPath(filePath);
        switch (fileTarget.getTargetKind()) {
            case OSS:
                try {
                    ossClient.upload(filePath, fileStream, materialEntity.getMaterialTarget().getRootPath());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case LOCAL:
                File f = FileUtils.getFile(fileTarget.getRootUrl(), fileTarget.getRootPath(), filePath);
                FileUtils.forceMkdir(f);
                FileUtils.copyInputStreamToFile(fileStream, f);
                break;
            default:
                return null;
        }
        materialMapper.insertMaterial(materialEntity);
        boolean isUrl = UrlValidator.getInstance().isValid(materialEntity.getMaterialTarget().getRootUrl());
        return new MaterialVO(
                materialEntity.getId(),
                materialEntity.getName(),
                isUrl ? new URI(materialEntity.getMaterialTarget().getRootUrl()).resolve(FilenameUtils.separatorsToUnix(Path.of(fileTarget.getRootPath(), materialEntity.getPath()).toString())).toString()
                        : FilenameUtils.separatorsToUnix(Path.of(materialEntity.getMaterialTarget().getRootUrl(), fileTarget.getRootPath(), materialEntity.getPath()).toString())
        );
    }

}

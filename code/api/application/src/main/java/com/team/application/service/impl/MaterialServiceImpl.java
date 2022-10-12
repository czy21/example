package com.team.application.service.impl;

import com.team.application.model.dto.MaterialDTO;
import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.application.util.MultipartFileUtil;
import com.team.domain.entity.MaterialEntity;
import com.team.domain.entity.MaterialTargetEntity;
import com.team.domain.kind.MaterialTargetKind;
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
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

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

    private Map<MaterialTargetKind, Consumer<MaterialEntity>> getMaterialTargetFunc(InputStream fileStream) {
        Map<MaterialTargetKind, Consumer<MaterialEntity>> targetMap = new HashMap<>();
        targetMap.put(MaterialTargetKind.OSS,
                t -> {
                    try {
                        ossClient.put(t.getPath(), fileStream, t.getMaterialTarget().getRootPath(), Map.of("fileName", t.getName()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        targetMap.put(MaterialTargetKind.LOCAL,
                t -> {
                    try {
                        File f = FileUtils.getFile(t.getMaterialTarget().getRootUrl(), t.getMaterialTarget().getRootPath(), t.getPath());
                        FileUtils.forceMkdir(f);
                        FileUtils.copyInputStreamToFile(fileStream, f);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return targetMap;
    }

    private MaterialVO singleFileStore(MaterialDTO dto, InputStream fileStream, String targetKey) throws Exception {
        String filePath = FilenameUtils.separatorsToUnix(Path.of(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), dto.getPath()).toString());
        Assert.notNull(targetKey, "file_target_key not null");
        MaterialTargetEntity fileTarget = Optional.ofNullable(materialMapper.selectMaterialTargetByKey(targetKey)).orElse(new MaterialTargetEntity());
        Assert.notNull(fileTarget.getKey(), "file.targetKey_no_exist");
        MaterialEntity me = new MaterialEntity();
        me.setId(UUID.randomUUID().toString());
        me.setName(dto.getName());
        me.setKind(dto.getMediaType());
        me.setMaterialTarget(fileTarget);
        me.setPath(filePath);
        Consumer<MaterialEntity> materialEntityConsumer = getMaterialTargetFunc(fileStream).get(me.getMaterialTarget().getTargetKind());
        if (materialEntityConsumer == null) {
            throw new RuntimeException(me.getMaterialTarget().getTargetKind().name() + " no exists");
        }
        materialEntityConsumer.accept(me);
        materialMapper.insertMaterial(me);
        boolean isUrl = UrlValidator.getInstance().isValid(me.getMaterialTarget().getRootUrl());
        return new MaterialVO(
                me.getId(),
                me.getName(),
                isUrl ? new URI(me.getMaterialTarget().getRootUrl()).resolve(FilenameUtils.separatorsToUnix(Path.of(fileTarget.getRootPath(), me.getPath()).toString())).toString()
                        : FilenameUtils.separatorsToUnix(Path.of(me.getMaterialTarget().getRootUrl(), fileTarget.getRootPath(), me.getPath()).toString())
        );
    }

    @Override
    public InputStream getStreamByUid(String uid) throws Exception {
        MaterialEntity me = materialMapper.selectById(uid);
        return getStreamBy(me);
    }

    @Override
    public InputStream getStreamBy(MaterialEntity me) throws Exception {
        MaterialTargetKind targetKind = me.getMaterialTarget().getTargetKind();
        switch (targetKind) {
            case OSS:
                return ossClient.get(me.getPath(), me.getMaterialTarget().getRootPath());
            case LOCAL:
                File f = Paths.get(me.getMaterialTarget().getRootUrl(), me.getMaterialTarget().getRootPath(), me.getPath()).toFile();
                return FileUtils.openInputStream(f);
            default:
                throw new RuntimeException(targetKind.name() + " no exists");
        }
    }
}

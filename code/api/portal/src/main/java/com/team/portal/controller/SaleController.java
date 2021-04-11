package com.team.portal.controller;

import com.team.application.model.vo.FileVO;
import com.team.application.model.vo.MaterialVO;
import com.team.application.service.MaterialService;
import com.team.cooperated.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping(path = "sale")
@RestController
@Slf4j
public class SaleController extends BaseController {

    @Autowired
    MaterialService materialService;

    @PostMapping(path = "upload")
    public MaterialVO upload(FileVO fileVO) throws IOException {
        return materialService.upload(fileVO, "DEFAULT");
    }
}

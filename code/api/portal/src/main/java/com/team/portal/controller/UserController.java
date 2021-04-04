package com.team.portal.controller;


import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchVO;
import com.team.application.pocket.EnumGender;
import com.team.application.pocket.SpecialPerson;
import com.team.application.pocket.SpecialWoman;
import com.team.application.service.UserService;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping(path = "load")
    @EnumPocket(value = {
            EnumGender.class,
    })
    @SpecialPocket(value = {
            SpecialPerson.class,
            SpecialWoman.class
    })
    public PageDTO<UserDTO> load() {
        return new PageDTO<>();
    }

    @PostMapping(path = "search")
    public PageDTO<UserDTO> search(@RequestBody SearchVO<UserDTO> search) {

        return userService.findByPage(search);
    }

}


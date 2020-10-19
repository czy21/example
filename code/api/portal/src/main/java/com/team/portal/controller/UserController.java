package com.team.portal.controller;


import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchUserModel;
import com.team.application.pocket.EnumGender;
import com.team.application.pocket.SpecialPerson;
import com.team.application.pocket.SpecialWoman;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @GetMapping("load")
    @EnumPocket(value = {
            EnumGender.class,
    })
    @SpecialPocket(value = {
            SpecialPerson.class,
            SpecialWoman.class
    })
    public PageDTO<UserDTO> load(SearchUserModel search) {
        return new PageDTO<>();
    }

}


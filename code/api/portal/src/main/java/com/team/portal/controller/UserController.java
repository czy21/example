package com.team.portal.controller;


import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchUserModel;
import com.team.application.pocket.EnumGender;
import com.team.application.pocket.SpecialPerson;
import com.team.application.pocket.SpecialWoman;
import com.team.application.service.UserRoleService;
import com.team.application.service.UserService;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import com.team.cooperated.exception.BusinessErrorCode;
import com.team.cooperated.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("load")
    @EnumPocket(value = {
            EnumGender.class,
    })
    @SpecialPocket(value = {
            SpecialPerson.class,
            SpecialWoman.class
    })
    public PageDTO<UserDTO> load(SearchUserModel search) {
        throw new BusinessException(BusinessErrorCode.EXIST_USER);
//        return userService.getUserPageListBy(search);
    }

    @PostMapping("search")
    public PageDTO<UserDTO> search(SearchUserModel search) {
        return userService.getUserPageListBy(search);
    }

    @PostMapping("add")
    public UserDTO add(@RequestBody UserDTO dto) {
        return userService.insertDefaultPwd(dto);
    }

    @PostMapping("edit")
    public UserDTO edit(@RequestBody UserDTO dto) {
        return userService.editUser(dto);
    }

    @PostMapping("modified")
    public Boolean modified(UserDTO dto) {
        return userService.modifiedUser(dto);
    }

    @PostMapping("userRoleDetails")
    public List<String> userRoleDetails(String userId) {
        return userRoleService.getRolesByUserId(userId);
    }
}


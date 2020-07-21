package com.team.portal.controller;


import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.page.PageInput;
import com.team.application.model.vo.BaseImportVO;
import com.team.application.model.vo.SearchUserModel;
import com.team.application.model.vo.UserVO;
import com.team.application.pocket.EnumGender;
import com.team.application.pocket.SpecialPerson;
import com.team.application.pocket.SpecialWoman;
import com.team.application.service.UserRoleService;
import com.team.application.service.UserService;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import com.team.infrastructure.lock.DataLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


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
        PageInput pageInput = new PageInput();
        pageInput.setPageIndex(1);
        pageInput.setPageSize(10);
        return userService.getUserPageListBy(pageInput, new UserVO());
    }

    @PostMapping("search")
    public PageDTO<UserDTO> search(SearchUserModel search) {
//        return userService.getUserPageListBy(search);
        return null;
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

    @PostMapping(path = "import")
    public Boolean userImport(BaseImportVO importVO) throws IOException {
        userService.userImport(importVO);
        return true;
    }

    @PostMapping(path = "match")
    @DataLock(prefix = "manualRinse", value = "#input")
    public void Match(@RequestBody Map<String, Object> input) throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("aa");
    }

}


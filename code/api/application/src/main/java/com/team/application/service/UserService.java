package com.team.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team.application.model.dto.PageDTO;
import com.team.application.model.dto.UserDTO;
import com.team.application.model.vo.SearchVO;
import com.team.domain.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    PageDTO<UserDTO> findByPage(SearchVO<UserDTO> search);

}

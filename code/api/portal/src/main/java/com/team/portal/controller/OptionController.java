package com.team.portal.controller;


import com.learning.infranstructure.model.SimpleItemModel;
import com.learning.web.service.OptionService;
import com.team.application.model.query.OptionQuery;
import com.team.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "option")
public class OptionController {

    @Autowired
    OptionService optionService;

    @Autowired
    UserService userService;
    @PostMapping(path = "query")
    public Map<String, List<? extends SimpleItemModel<?>>> query(@RequestBody OptionQuery query) {
        return optionService.findByKeys(query.getKeys());
    }
}

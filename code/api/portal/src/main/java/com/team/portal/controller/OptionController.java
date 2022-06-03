package com.team.portal.controller;

import com.learning.web.model.SimpleItemModel;
import com.learning.web.service.OptionService;
import com.team.application.model.query.OptionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "option")
public class OptionController {

    @Autowired
    OptionService optionService;

    @PostMapping(path = "query")
    public Map<String, List<? extends SimpleItemModel<?>>> query(@RequestBody OptionQuery query) {
        return optionService.findByKeys(query.getKeys());
    }
}

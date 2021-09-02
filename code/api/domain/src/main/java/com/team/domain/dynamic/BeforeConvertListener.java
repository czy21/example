package com.team.domain.dynamic;

import com.team.domain.mongo.entity.ExtensionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BeforeConvertListener extends AbstractMongoEventListener<Object> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        log.info("onBeforeConvert");
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        log.info("onBeforeSave");
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        log.info("onAfterSave");
    }

    @Override
    public void onAfterLoad(AfterLoadEvent<Object> event) {
        log.info("onAfterLoad");
    }

    @Override
    public void onAfterConvert(AfterConvertEvent<Object> event) {
        if (event.getSource() instanceof ExtensionModel) {
            ExtensionModel basicSource = (ExtensionModel) event.getSource();
            Map<String,Object> extension  = event.getDocument().entrySet().stream()
                    .filter(d -> d.getKey().startsWith("extension"))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            basicSource.setExtension(extension);
        }
        log.info("onAfterConvert");
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Object> event) {
        log.info("onAfterDelete");
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        log.info("onBeforeDelete");
    }
}
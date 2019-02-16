package com.team.configure;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UidGenService {

    private static UidGenerator uidGenerator;

    @Resource
    private void setUidGenerator(UidGenerator uid) {
        uidGenerator = uid;
    }

    public static long getUid() {
        return uidGenerator.getUID();
    }
}

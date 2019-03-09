package com.team.core.configure;


import com.team.core.annotations.Pocket;
import com.team.core.annotations.Pockets;
import com.team.extension.*;

import java.util.Arrays;
import java.util.HashMap;

public class PocketConfigure {

    public static HashMap<String, Object> InjectDatas(Pockets pockets) {
        HashMap<String, Object> hash = new HashMap<>();
        Arrays.stream(pockets.value()).forEach((t) -> hash.putAll(InjectData(t)));
        return hash;
    }

    public static HashMap<String, Object> InjectData(Pocket p) {
        HashMap<String, Object> hash = new HashMap<>();
        String suggestKey = p.entity().getSimpleName().toLowerCase() + "s";
        switch (p.entity().getSimpleName()) {
            case "User":
                hash.put(suggestKey, UserExtension.convertToSimple());
                break;
            case "Role":
                hash.put(suggestKey, RoleExtension.convertToSimple());
                break;
            case "Menu":
                hash.put(suggestKey, MenuExtension.convertToSimple());
                if (p.obtainTree()) {
                    hash.put("menuTree", MenuExtension.convertToTreeSimple());
                }
                break;
            case "Department":
                hash.put(suggestKey, DepartmentExtension.convertToSimple());
                break;
            case "Company":
                hash.put(suggestKey, CompanyExtension.convertToSimple());
                break;
            case "Function":
                hash.put(suggestKey, FunctionExtension.convertToSimple());
        }
        return hash;
    }

}



package com.team.core.configure;


import com.team.extension.entity.*;

import java.util.HashMap;
import java.util.List;

public class PocketConfigure {

    public static HashMap<String, Object> InjectData(List<Class> clazz) {
        HashMap<String, Object> hash = new HashMap<>();
        clazz.forEach((t) -> {
            String suggestKey = t.getSimpleName().toLowerCase() + "s";
            switch (t.getSimpleName()) {
                case "User":
                    hash.put(suggestKey, UserExtension.convertToSimple());
                    break;
                case "Role":
                    hash.put(suggestKey, RoleExtension.convertToSimple());
                    break;
                case "Menu":
                    hash.put(suggestKey, MenuExtension.convertToSimple());
                    break;
                case "Department":
                    hash.put(suggestKey, DepartmentExtension.convertToSimple());
                    break;
                case "Company":
                    hash.put(suggestKey, CompanyExtension.convertToSimple());
                    break;
            }
        });
        return hash;
    }

}



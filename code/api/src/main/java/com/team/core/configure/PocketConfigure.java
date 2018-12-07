package com.team.core.configure;


import com.team.core.extension.entity.DepartmentExtension;
import com.team.core.extension.entity.MenuExtension;
import com.team.core.extension.entity.RoleExtension;
import com.team.core.extension.entity.UserExtension;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class PocketConfigure {

    public static HashMap<String, Object> InjectData(List<Class> clazz) {
        HashMap<String, Object> hash = new HashMap<>();
        clazz.forEach((t) -> {
            switch (t.getSimpleName()) {
                case "User":
                    hash.put(t.getSimpleName().toLowerCase() + "s", UserExtension.convertToSimple());
                    break;
                case "Role":
                    hash.put(t.getSimpleName().toLowerCase() + "s", RoleExtension.convertToSimple());
                    break;
                case "Menu":
                    hash.put(t.getSimpleName().toLowerCase() + "s", MenuExtension.convertToSimple());
                    break;
                case "Department":
                    hash.put(t.getSimpleName().toLowerCase() + "s", DepartmentExtension.convertToSimple());
                    break;
                default:
                    break;
            }
        });
        return hash;
    }

}



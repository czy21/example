package com.team.core.configure;


import com.team.core.extension.entity.DepartmentExtensions;
import com.team.core.extension.entity.MenuExtensions;
import com.team.core.extension.entity.RoleExtensions;
import com.team.core.extension.entity.UserExtensions;

import java.util.HashMap;
import java.util.List;

public class PocketConfigure {

    public static HashMap<String, Object> InjectData(List<Class> clazz) {
        HashMap<String, Object> hash = new HashMap<>();
        clazz.forEach((t) -> {
            switch (t.getSimpleName()) {
                case "User":
                    hash.put(t.getSimpleName().toLowerCase() + "s", UserExtensions.ConvertToSimple());
                    break;
                case "Role":
                    hash.put(t.getSimpleName().toLowerCase() + "s", RoleExtensions.ConvertToSimple());
                    break;
                case "Menu":
                    hash.put(t.getSimpleName().toLowerCase() + "s", MenuExtensions.ConvertToSimple());
                    break;
                case "Department":
                    hash.put(t.getSimpleName().toLowerCase() + "s", DepartmentExtensions.ConvertToSimple());
                    break;
                default:
                    break;
            }
        });
        return hash;
    }

}



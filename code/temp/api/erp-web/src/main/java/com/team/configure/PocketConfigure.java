package com.team.configure;


import com.team.extension.DepartmentExtensions;
import com.team.extension.MenuExtensions;
import com.team.extension.UserExtensions;

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



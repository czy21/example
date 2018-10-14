package com.czy.core.mvc;


import com.czy.core.extensions.entity.MenuExtensions;
import com.czy.core.extensions.entity.UserExtensions;

import java.util.HashMap;
import java.util.List;

public class PocketConfig {

    public static HashMap<String, Object> InjectData(List<Class> clazz) {
        HashMap hash = new HashMap<String, Object>();
        clazz.forEach((t) -> {
            switch (t.getSimpleName()) {
                case "User":
                    hash.put(t.getSimpleName().toLowerCase() + "s", UserExtensions.ConvertToSimple());
                    break;
                case "Menu":
                    hash.put(t.getSimpleName().toLowerCase() + "s", MenuExtensions.ConvertToSimple());
                    break;
                default:
                    break;
            }
        });
        return hash;
    }

}



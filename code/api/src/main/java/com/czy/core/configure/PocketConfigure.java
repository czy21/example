package com.czy.core.configure;


import com.czy.core.extension.entity.MenuExtensions;
import com.czy.core.extension.entity.UserExtensions;

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
                default:
                    break;
            }
        });
        return hash;
    }

}



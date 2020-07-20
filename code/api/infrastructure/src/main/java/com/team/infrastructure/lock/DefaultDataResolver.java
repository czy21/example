package com.team.infrastructure.lock;

public class DefaultDataResolver implements DataResolver {
    @Override
    public String encodeToIdentify(Object value) {
        return value.toString();
    }
}

package com.team.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.security.session.SessionInterceptor;
import com.team.security.session.SessionRepositoryAdapter;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.context.annotation.Bean;

import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableSpringHttpSession
public class SecurityConfigure implements WebMvcConfigurer {

    @Bean
    public SessionRepository<?> customSessionRepository(StringRedisTemplate redisTemplate,
                                                        ObjectMapper objectMapper) {
        return new SessionRepositoryAdapter(redisTemplate, objectMapper);
    }

    @Bean
    public DefaultCookieSerializerCustomizer cookieSerializerCustomizer() {
        return t -> {
            t.setCookieName(SessionInterceptor.COOKIE_NAME);
            t.setUseBase64Encoding(false);
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor());
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

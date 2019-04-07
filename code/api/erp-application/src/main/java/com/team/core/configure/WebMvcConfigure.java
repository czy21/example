package com.team.core.configure;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfigure extends WebMvcConfigurationSupport {

    @Value("${allowed-origin.url}")
    private String url;

    /*
     * @author 陈昭宇
     * @description 配置跨域
     * @date 2018/12/27 16:15
     * @param []
     * @return org.springframework.web.filter.CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(url);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        source.registerCorsConfiguration("/api/**", corsConfiguration); // 对接口配置跨域设置
        return new CorsFilter(source);
    }

    /*
     * @author 陈昭宇
     * @description 忽略静态资源拦截
     * @date 2018/12/27 16:16
     * @param [registry]
     * @return void
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/META-INF/resources/favicon.ico");
        super.addResourceHandlers(registry);
    }

    /*
     * @author 陈昭宇
     * @description 配置请求url大小写均可请求
     * @date 2018/12/27 16:17
     * @param []
     * @return org.springframework.web.servlet.config.annotation.PathMatchConfigurer
     */
    @Override
    protected PathMatchConfigurer getPathMatchConfigurer() {
        PathMatchConfigurer configure = super.getPathMatchConfigurer();
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configure.setPathMatcher(matcher);
        return configure;
    }

    /*
     * @author 陈昭宇
     * @description 配置消息转换器(格式化)
     * @date 2018/12/27 16:18
     * @param [converters]
     * @return void
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.BrowserCompatible);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }
}

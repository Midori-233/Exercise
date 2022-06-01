package com.sendriods.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")    //添加映射路径，“/**”表示对所有的路径实行全局跨域访问权限的设置
                        .allowedOriginPatterns("*")
                        //.allowCredentials(true)  //是否允许发送Cookie信息
                        /*
                         * FIXME
                         * Resolved [java.lang.IllegalArgumentException: When allowCredentials is true,
                         *  allowedOrigins cannot contain the special value "*" since that cannot be set on the
                         * "Access-Control-Allow-Origin" response header. To allow credentials to a set of origins,
                         * list them explicitly or consider using "allowedOriginPatterns" instead.]
                         * */
                        .allowedMethods("*")     //开放哪些Http方法，允许跨域访问
                        .allowedHeaders("*")     //允许HTTP请求中的携带哪些Header信息
                        .exposedHeaders("*");   //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
            }
        };
    }
}
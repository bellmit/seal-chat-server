package com.mn.im.core.common.extend.config;

import com.mn.im.core.common.extend.common.util.UserInfoUtils;
import com.mn.im.core.common.extend.config.resolver.LoginUserArgumentResolver;
import com.mn.im.core.common.extend.filter.ParameterFilter;
import com.mn.im.core.common.extend.filter.WebTokenFilter;
import com.mn.im.core.common.extend.interceptor.ParameterInterceptor;
import com.mn.im.core.common.extend.interceptor.RoleInterfaceInterceptor;
import com.mn.im.core.common.extend.mybatis.interceptor.SQLInterceptor;
import com.mn.im.core.common.extend.service.ConstantService;
import com.mn.im.core.common.extend.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author qiaomengnan
 * @ClassName: WebExtendConfigurer
 * @Description: 项目共通配置类
 * @date 2018/1/7
 */
@Slf4j
@Configuration
public class WebExtendConfigurer extends WebMvcConfigurerAdapter implements AsyncConfigurer {

    @Bean
    public WebProperties webProperties(@Autowired(required = false) DataSource dataSource){
        if(dataSource != null) {
            try {
                dataSource.getConnection();
                log.info("***************************数据库连接池初始化***************************");
            } catch (Exception ex) {
                log.error(ex.getMessage());
                ex.printStackTrace();
                log.info("***************************数据库连接池初始化失败***************************");
            }
        }
        log.info("***************************webProperties初始化***************************");
        return new WebProperties();
    }

    @Bean
    public WebServiceNames webServiceNames(){
        log.info("***************************webServiceNames初始化***************************");
        return new WebServiceNames();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        log.info("***************************corsFilter初始化***************************");
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


    @Bean
    public LoginUserArgumentResolver loginUserArgumentResolver(){
        log.info("***************************loginUserArgumentResolver初始化***************************");
        return new LoginUserArgumentResolver();
    }

    @Bean
    public UserInfoUtils userInfoUtil(){
        log.info("***************************userInfoUtil初始化***************************");
        return new UserInfoUtils();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisTemplate redisTemplateInit(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        log.info("***************************redisTemplateInit初始化***************************");
        return redisTemplate;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(5000);
        executor.setQueueCapacity(500);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Bean
    public RoleInterfaceInterceptor roleInterfaceInterceptor() {
        return new RoleInterfaceInterceptor();
    }

    @Autowired(required = false)
    private LogService logService;

    @Autowired(required = false)
    private ConstantService constantService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleInterfaceInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new ParameterInterceptor(logService,constantService)).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean parameterFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ParameterFilter());
        registration.addUrlPatterns("/*");
        registration.setName("parameterFilter");
        registration.setOrder(20000);
        log.info("***************************parameterFilter初始化***************************");
        return registration;
    }

    @Bean
    public WebTokenFilter webTokenFilter(){
        return new WebTokenFilter();
    }


    @Bean
    public FilterRegistrationBean webTokenFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(webTokenFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("webTokenFilter");
        registrationBean.setOrder(1000);
        log.info("***************************webTokenFilter初始化***************************");
        return registrationBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("***************************passwordEncoder初始化***************************");
        return new BCryptPasswordEncoder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate commonRestTemplate(WebProperties webProperties){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        if(webProperties.getHystrixTimeOut() == null)
            httpRequestFactory.setReadTimeout(120 * 1000);
        else
            httpRequestFactory.setReadTimeout(webProperties.getHystrixTimeOut());
        return new RestTemplate(httpRequestFactory);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 文件上传路径 */
        registry.addResourceHandler("/profile/**").addResourceLocations("file:" + WebFilePaths.fileRootPath());
    }

    @Bean
    public SQLInterceptor sqlInterceptor() {
        SQLInterceptor sqlInterceptor = new SQLInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        sqlInterceptor.setProperties(properties);
        return sqlInterceptor;
    }

}

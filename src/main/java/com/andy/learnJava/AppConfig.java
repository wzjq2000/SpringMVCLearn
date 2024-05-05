package com.andy.learnJava;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.Servlet5Loader;
import io.pebbletemplates.spring.servlet.PebbleViewResolver;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@EnableWebMvc
@ComponentScan
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/jdbc.properties")
public class AppConfig {

    //WebMvcConfigurer并不是必须的，但我们在这里创建一个默认的WebMvcConfigurer，只覆写addResourceHandlers()，目的是让Spring MVC自动处理静态文件，并且映射路径为/static/**。
    @Bean
    WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("/static/");
            }
        };
    }


    // ViewResolver通过指定prefix和suffix来确定如何查找View。上述配置使用Pebble引擎，指定模板文件存放在/WEB-INF/templates/目录下。
    @Bean
    ViewResolver createViewResolver( ServletContext servletContext){
        PebbleEngine engine = new PebbleEngine.Builder().autoEscaping(true)
                .cacheActive(false)
                .loader(new Servlet5Loader(servletContext))
                .build();
        PebbleViewResolver viewResolver = new PebbleViewResolver(engine);
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix("");
        return viewResolver;

    }
//
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:Blog/yourdatabase?serverTimezone=UTC");
//        dataSource.setUsername("root");
//        dataSource.setPassword("Wz970630");
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(DataSource ds){
//        return new DataSourceTransactionManager(ds);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//


}

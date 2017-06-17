package com.cricketerlk;

//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Supun on 5/8/2017.
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/dist/index.html");
        registry.addViewController("/login").setViewName("forward:/dist/index.html");
        registry.addViewController("/rules").setViewName("forward:/dist/index.html");
        registry.addViewController("/about-us").setViewName("forward:/dist/index.html");
        registry.addViewController("/admin").setViewName("forward:/dist/index.html");
        registry.addViewController("/play").setViewName("forward:/dist/index.html");
        registry.addViewController("/schedule").setViewName("forward:/dist/index.html");
        registry.addViewController("/results").setViewName("forward:/dist/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configure) {
        super.configurePathMatch(configure);
        configure.setUseSuffixPatternMatch(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dist/**").addResourceLocations("classpath:dist/");
    }

}

package com.backbase.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public String[] getServletMappings() {
        return new String[]{"/api/*"};
    }

    @Override
    public Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    public Class<?>[] getServletConfigClasses() {
        return null;
    }

}
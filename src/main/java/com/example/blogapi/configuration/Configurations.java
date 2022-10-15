package com.example.blogapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.MultipartFilter;

public class Configurations {

    @Bean
    public MultipartFilter multipartFilter(){

        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

}

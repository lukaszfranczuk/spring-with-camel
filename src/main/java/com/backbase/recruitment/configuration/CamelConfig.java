package com.backbase.recruitment.configuration;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.backbase.recruitment.route")
public class CamelConfig extends CamelConfiguration {
}

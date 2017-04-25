package com.backbase.configuration;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.backbase.atm")
public class CamelConfig extends CamelConfiguration {
}

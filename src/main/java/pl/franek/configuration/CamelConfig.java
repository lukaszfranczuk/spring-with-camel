package pl.franek.configuration;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("pl.backbase.atm")
public class CamelConfig extends CamelConfiguration {
}

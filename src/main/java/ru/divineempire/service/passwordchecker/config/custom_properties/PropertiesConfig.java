package ru.divineempire.service.passwordchecker.config.custom_properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LengthCheckProperties.class)
public class PropertiesConfig {

}

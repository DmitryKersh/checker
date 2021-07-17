package ru.divineempire.service.passwordchecker.config.custom_properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.lengthcheck")
@Data
public class LengthCheckProperties {
    private int min = 8;
    private int max = 30;
}

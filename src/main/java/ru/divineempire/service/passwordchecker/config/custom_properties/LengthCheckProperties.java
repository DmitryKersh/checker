package ru.divineempire.service.passwordchecker.config.custom_properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.lengthcheck")
public class LengthCheckProperties {
    @Getter @Setter
    private int min = 8;

    @Getter @Setter
    private int max = 30;
}

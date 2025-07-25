package com.mla.socialchristian.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfiguration {
    public static String KEY;
    public static Long EXPIRATION;

    public void setKey(String key) {
        KEY = key;
    }

    public void setExpiration(Long expiration) {
        EXPIRATION = expiration;
    }
}

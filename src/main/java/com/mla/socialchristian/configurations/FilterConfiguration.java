package com.mla.socialchristian.configurations;

import com.mla.socialchristian.domain.interfaces.repository.IBlacklistTokenRepository;
import com.mla.socialchristian.security.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public JWTFilter jwtFilter(IBlacklistTokenRepository blacklistTokenRepository) {
        return new JWTFilter(blacklistTokenRepository);
    }
}

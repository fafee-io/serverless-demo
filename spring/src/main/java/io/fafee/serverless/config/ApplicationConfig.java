package io.fafee.serverless.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@EnableJpaRepositories(basePackages = {"io.fafee.serverless"})
@EntityScan(basePackages = {"io.fafee.serverless"})
@EnableJpaAuditing
public class ApplicationConfig {

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("Europe/Budapest"));
    }

}

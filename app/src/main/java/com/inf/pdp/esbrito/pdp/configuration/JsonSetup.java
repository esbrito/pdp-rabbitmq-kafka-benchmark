package com.inf.pdp.esbrito.pdp.configuration;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonSetup {

    @Bean
    public Gson parser() {
        return new Gson();
    }

}

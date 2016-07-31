package com.tomkasp.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tomkasp.domain.util.*;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.*;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        JodaModule jodaModule = new JodaModule();
//        JavaTimeModule module = new JavaTimeModule();
//        module.addSerializer(OffsetDateTime.class, JSR310DateTimeSerializer.INSTANCE);
//        module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
//        module.addSerializer(LocalDateTime.class, JSR310DateTimeSerializer.INSTANCE);
//        module.addSerializer(Instant.class, JSR310DateTimeSerializer.INSTANCE);
//        module.addDeserializer(LocalDate.class, JSR310LocalDateDeserializer.INSTANCE);

        final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder(){

            @Override
            public void configure(ObjectMapper objectMapper) {
                super.configure(objectMapper);
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            }

        };

        return jackson2ObjectMapperBuilder
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .featuresToEnable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
            .featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
            .findModulesViaServiceLoader(true)
            .modulesToInstall(jodaModule);
    }
}

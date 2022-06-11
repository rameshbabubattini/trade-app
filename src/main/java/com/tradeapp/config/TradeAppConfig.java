package com.tradeapp.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class TradeAppConfig {

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        return builder -> {
//          builder.simpleDateFormat("dd/MM/yyyy");
//          builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//          builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//        };
//    }

}

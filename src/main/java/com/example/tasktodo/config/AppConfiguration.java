package com.example.tasktodo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Data
@Configuration
@Profile("dev")
public class AppConfiguration implements IAppConfiguration{

    @Value("${page.size}")
    private int pageSize;

    @Value("${page.start}")
    private int pageStart;

//    @Value("${password}")
//    private String password;
}

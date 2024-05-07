package com.example.tasktodo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Data
@Profile("default")
public class DefaultAppConfiguration implements IAppConfiguration{
    @Override
    public int getPageSize() {
        return 5;
    }

    @Override
    public int getPageStart() {
        return 1;
    }

    @Value("${page.theme}")
    private String pageTheme;

//    @Override
//    public String getPassword() {
//        return "PASSWORD";
//    }
}

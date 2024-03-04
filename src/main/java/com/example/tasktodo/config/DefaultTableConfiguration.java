package com.example.tasktodo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultTableConfiguration implements ITableConfiguration{
    @Override
    public String getTableColor() {
        return "table-dark";
    }
}

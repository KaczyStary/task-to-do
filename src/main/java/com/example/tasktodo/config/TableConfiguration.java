package com.example.tasktodo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Data
public class TableConfiguration implements ITableConfiguration{

    @Value("${table-color}")
    private String tableColor;

}

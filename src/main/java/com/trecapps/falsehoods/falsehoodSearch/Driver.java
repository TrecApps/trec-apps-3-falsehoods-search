package com.trecapps.falsehoods.falsehoodSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.trecapps.base.FalsehoodModel","com.trecapps.falsehoods.falsehoodSearch"})
@EnableJpaRepositories(basePackages = {"com.trecapps.base.FalsehoodModel.repos", "com.trecapps.base.InfoResource.repos"})
public class Driver {

    public static void main(String[] args) {
        SpringApplication.run(Driver.class, args);
    }

}

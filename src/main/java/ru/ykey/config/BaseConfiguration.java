package ru.ykey.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * User: y.krivochurov
 * Date: 28.04.13
 * Time: 11:27
 */
@Configuration
@EnableJpaRepositories(value = {"ru.ykey.dao"})
@ComponentScan(basePackages = {"ru.ykey.service"})
public class BaseConfiguration {
}
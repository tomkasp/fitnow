package com.tomkasp.fitnow.shop.infrastructure;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.tomkasp.fitnow.shop.infrastructure")
public class ShopConfiguration {
}

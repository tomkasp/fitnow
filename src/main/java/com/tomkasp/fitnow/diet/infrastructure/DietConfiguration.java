package com.tomkasp.fitnow.diet.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Tomasz Kasprzycki
 */
@Configuration
@EnableJpaRepositories("com.tomkasp.fitnow.diet.domain")
public class DietConfiguration {

}

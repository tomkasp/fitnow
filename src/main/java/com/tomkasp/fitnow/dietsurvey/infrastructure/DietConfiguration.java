package com.tomkasp.fitnow.dietsurvey.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Tomasz Kasprzycki
 */
@Configuration
@EnableJpaRepositories("com.tomkasp.fitnow.dietsurvey.domain")
public class DietConfiguration {

}

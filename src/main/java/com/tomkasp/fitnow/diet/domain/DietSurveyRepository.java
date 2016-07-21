package com.tomkasp.fitnow.diet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@Repository
public interface DietSurveyRepository extends JpaRepository<DietSurvey, Long> {

    Optional<DietSurvey> findByUserId(Long id);
}

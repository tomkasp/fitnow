package com.tomkasp.repository;

import com.tomkasp.domain.Meal;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Meal entity.
 */
public interface MealRepository extends JpaRepository<Meal,Long> {

    @Query("select distinct meal from Meal meal left join fetch meal.products")
    List<Meal> findAllWithEagerRelationships();

    @Query("select meal from Meal meal left join fetch meal.products where meal.id =:id")
    Meal findOneWithEagerRelationships(@Param("id") Long id);

}

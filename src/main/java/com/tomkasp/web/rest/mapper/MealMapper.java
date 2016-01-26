package com.tomkasp.web.rest.mapper;

import com.tomkasp.domain.*;
import com.tomkasp.web.rest.dto.MealDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Meal and its DTO MealDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, })
public interface MealMapper {

    MealDTO mealToMealDTO(Meal meal);

    Meal mealDTOToMeal(MealDTO mealDTO);

    default Product productFromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}

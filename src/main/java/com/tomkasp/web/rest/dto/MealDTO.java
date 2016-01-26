package com.tomkasp.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.tomkasp.domain.enumeration.MealType;

/**
 * A DTO for the Meal entity.
 */
public class MealDTO implements Serializable {

    private Long id;

    private MealType type;


    private Set<ProductDTO> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MealDTO mealDTO = (MealDTO) o;

        if ( ! Objects.equals(id, mealDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MealDTO{" +
            "id=" + id +
            ", type='" + type + "'" +
            '}';
    }
}

package com.tomkasp.service;

import com.tomkasp.domain.Product;
import com.tomkasp.web.rest.dto.ProductDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Product.
 */
public interface ProductService {

    /**
     * Save a product.
     * @return the persisted entity
     */
    public ProductDTO save(ProductDTO productDTO);

    /**
     *  get all the products.
     *  @return the list of entities
     */
    public List<ProductDTO> findAll();

    /**
     *  get the "id" product.
     *  @return the entity
     */
    public ProductDTO findOne(Long id);

    /**
     *  delete the "id" product.
     */
    public void delete(Long id);
}

package com.tomkasp.service;

import com.tomkasp.domain.Author;
import com.tomkasp.web.rest.dto.AuthorDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Author.
 */
public interface AuthorService {

    /**
     * Save a author.
     * @return the persisted entity
     */
    public AuthorDTO save(AuthorDTO authorDTO);

    /**
     *  get all the authors.
     *  @return the list of entities
     */
    public List<AuthorDTO> findAll();

    /**
     *  get the "id" author.
     *  @return the entity
     */
    public AuthorDTO findOne(Long id);

    /**
     *  delete the "id" author.
     */
    public void delete(Long id);
}

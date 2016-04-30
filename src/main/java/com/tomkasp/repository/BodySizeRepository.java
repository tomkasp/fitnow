package com.tomkasp.repository;


import com.tomkasp.profile.BodySize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface BodySizeRepository extends JpaRepository<BodySize, Long> {

    Optional<BodySize> findByUserId(Long userId);
}

package com.tomkasp.repository;


import com.tomkasp.profile.domain.BodySize;
import com.tomkasp.profile.domain.BodySizeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface BodySizeRepository extends JpaRepository<BodySize, Long> {

    Optional<BodySize> findByUserId(Long userId);

    @Query("SELECT bs from BodySize bs JOIN FETCH bs.bodySizeHistories WHERE bs.user.id = (:userId) order by bs.id asc ")
    Optional<BodySize> findHistoryByUserId(@Param("userId") Long userId);
}

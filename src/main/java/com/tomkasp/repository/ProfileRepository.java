package com.tomkasp.repository;

import com.tomkasp.fitnow.profile.domain.Profile;
import com.tomkasp.fitnow.sharedkernel.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUserId(Long userId);

    @Modifying
    @Query("update Profile p set  p.height =:height, p.sex=:sex where p.user.id=:userId")
    void updateProfile(@Param("height") Integer height, @Param("sex") Sex sex, @Param("userId") Long userId);
}

package com.tomkasp.repository;

import com.tomkasp.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}

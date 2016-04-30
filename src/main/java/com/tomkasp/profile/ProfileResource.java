package com.tomkasp.profile;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProfileResource {

    private final Logger log = LoggerFactory.getLogger(ProfileResource.class);

    private final ProfileService profileService;

    @Autowired
    public ProfileResource(ProfileService profileService) {
        this.profileService = profileService;
    }


    @RequestMapping(value = "/profiles",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ProfileOutDTO> getProfiles() {
        log.debug("Rest request to get the profiles");
        return profileService.findAll();
    }

    @RequestMapping(value = "/profilemine",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProfileOutDTO> getMineProfile() {
        log.debug("Rest request to get a mine profile: {}");
        ProfileOutDTO profileOutDTO = profileService.findMine();
        return Optional.ofNullable(profileOutDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/profiles/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProfileOutDTO> getProfile(@PathVariable Long id) {
        log.debug("Rest request to get a profile: {}", id);
        ProfileOutDTO profileInDTO = profileService.findOne(id);
        return Optional.ofNullable(profileInDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/profiles",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProfileOutDTO> updateProfile(@Valid @RequestBody ProfileInDTO profileInDTO) throws URISyntaxException {
        log.debug("REST request to update Product : {}", profileInDTO);
        if (profileInDTO.getId() == null) {
            return createProfile(profileInDTO);
        }
        ProfileOutDTO result = profileService.save(profileInDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("product", profileInDTO.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "/profiles",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProfileOutDTO> createProfile(@Valid @RequestBody ProfileInDTO profileInDTO) throws URISyntaxException {
        log.debug("REST request to save Profile : {}", profileInDTO);
        if (profileInDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("proflie", "idexists", "A new profile cannot already have an ID")).body(null);
        }
        ProfileOutDTO result = profileService.save(profileInDTO);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("product", result.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "/profiles/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        profileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("profile", id.toString())).build();
    }
}

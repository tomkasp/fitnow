package com.tomkasp.fitnow.profile.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.profile.dto.BodySizeDTO;
import com.tomkasp.fitnow.profile.application.BodySizeService;
import com.tomkasp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api")
public class BodySizeResource {

    private final Logger log = LoggerFactory.getLogger(BodySizeResource.class);

    private final BodySizeService bodySizeService;

    @Autowired
    public BodySizeResource(BodySizeService bodySizeService) {
        this.bodySizeService = bodySizeService;
    }

    @RequestMapping(value = "/bodysizes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BodySizeDTO> getMineProfileDetails() {
        log.debug("Rest request to get a body sizes");
        Optional<BodySizeDTO> bodySizeDTOOptional = bodySizeService.findMineBodySizes();
        return bodySizeDTOOptional
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/bodysizes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BodySizeDTO> updateMineProfileDetails(@RequestBody BodySizeDTO bodySizeDTO) throws URISyntaxException {
        log.debug("REST request to update body size : {}", bodySizeDTO);
        if (bodySizeDTO.getId() == null) {
            return createMineProfileDetails(bodySizeDTO);
        }
        BodySizeDTO result = bodySizeService.save(bodySizeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bodysize", bodySizeDTO.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "/bodysizes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BodySizeDTO> createMineProfileDetails(@RequestBody BodySizeDTO bodySizeDTO) throws URISyntaxException {
        log.debug("REST request to createNew body size : {}", bodySizeDTO);
        if (bodySizeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("body size", "idexists", "A new body size cannot already have an ID")).body(null);
        }
        BodySizeDTO result = bodySizeService.save(bodySizeDTO);
        return ResponseEntity.created(new URI("/api/bodysizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bodysize", result.getId().toString()))
            .body(result);
    }

}

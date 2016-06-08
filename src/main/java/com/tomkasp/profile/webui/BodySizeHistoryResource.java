package com.tomkasp.profile.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.profile.application.BodySizeHistoryService;
import com.tomkasp.profile.domain.BodySizeHistory;
import com.tomkasp.profile.dto.BodySizeHistoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping("/api/bodysizes/history")
public class BodySizeHistoryResource {

    private final Logger log = LoggerFactory.getLogger(BodySizeHistoryResource.class);

    private final BodySizeHistoryService bodySizeHistoryService;

    @Autowired
    public BodySizeHistoryResource(BodySizeHistoryService bodySizeHistoryService) {
        this.bodySizeHistoryService = bodySizeHistoryService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<BodySizeHistory> getMineBodySizesHistory() {
        log.debug("Rest request to get a body sizes history");
        return bodySizeHistoryService.getBodySizeHistory();
    }

}

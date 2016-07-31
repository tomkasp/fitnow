package com.tomkasp.fitnow.profile.webui;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.fitnow.profile.application.BodySizeHistoryService;
import com.tomkasp.fitnow.profile.domain.BodySizeHistory;
import com.tomkasp.fitnow.profile.dto.BodySizeHistoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<BodySizeHistoryDTO> getMineBodySizesHistory() {
        log.debug("Rest request to get a body sizes history");
        return bodySizeHistoryService.getBodySizeHistory();
    }

}

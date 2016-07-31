package com.tomkasp.fitnow.profile.application;

import com.tomkasp.fitnow.profile.domain.BodySize;
import com.tomkasp.fitnow.profile.domain.BodySizeHistory;
import com.tomkasp.fitnow.profile.dto.BodySizeHistoryDTO;
import com.tomkasp.repository.BodySizeRepository;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@Service
@Transactional(readOnly = true)
public class BodySizeHistoryService {

    private final Logger log = LoggerFactory.getLogger(BodySizeHistoryService.class);

    private final BodySizeRepository bodySizeRepository;
    private final UserService userService;
    private final BodySizeHistoryFactory bodySizeHistoryFactory;

    @Autowired
    public BodySizeHistoryService(BodySizeRepository bodySizeRepository, UserService userService, BodySizeHistoryFactory bodySizeHistoryFactory) {
        this.bodySizeRepository = bodySizeRepository;
        this.userService = userService;
        this.bodySizeHistoryFactory = bodySizeHistoryFactory;
    }

    public  List<BodySizeHistoryDTO> getBodySizeHistory() {
        final Long userId = userService.getUserWithAuthorities().getId();
        log.debug("Request to find user body size history for user id: {}", userId);
        List<BodySizeHistoryDTO> list = new ArrayList<>();
        final List<BodySizeHistory> bodySizeHistories = bodySizeRepository.findHistoryByUserId(userId)
            .map(BodySize::getBodySizeHistories).orElse(new ArrayList<>());

        bodySizeHistories.forEach(bodySizeHistory -> list.add(bodySizeHistoryFactory.toDto(bodySizeHistory)));
        return list;
    }
}

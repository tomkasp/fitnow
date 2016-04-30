package com.tomkasp.profile;

import com.tomkasp.repository.BodySizeRepository;
import com.tomkasp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional(readOnly = true)
public class BodySizeService {

    private final Logger log = LoggerFactory.getLogger(BodySizeService.class);

    private final BodySizeMapper bodySizeMapper;
    private final BodySizeRepository bodySizeRepository;
    private final UserService userService;

    @Autowired
    public BodySizeService(BodySizeMapper bodySizeMapper, BodySizeRepository bodySizeRepository, UserService userService) {
        this.bodySizeMapper = bodySizeMapper;
        this.bodySizeRepository = bodySizeRepository;
        this.userService = userService;
    }


    public Optional<BodySizeDTO> findMineBodySizes() {
        final Long userId = userService.getUserWithAuthorities().getId();
        log.debug("Request to find user body size for user id: {}", userId);
        return bodySizeRepository.findByUserId(userId).map(user ->
                bodySizeMapper.bodySizeToBodySizeDTO(user)
        );
    }

    @Transactional(readOnly = false)
    public BodySizeDTO save(BodySizeDTO bodySizeDTO) {
        log.debug("Request to save body size : {}", bodySizeDTO);
        BodySize bodySize = bodySizeMapper.bodySizeDTOToBodySize(bodySizeDTO);
        //TODO handle situation when user is not loged
        bodySize.setUser(userService.getUserWithAuthorities());
        bodySize = bodySizeRepository.save(bodySize);
        BodySizeDTO result = bodySizeMapper.bodySizeToBodySizeDTO(bodySize);
        return result;
    }
}

package com.tomkasp.fitnow.profile.application;

import com.tomkasp.fitnow.infrastructure.factory.DtoFactory;
import com.tomkasp.fitnow.profile.domain.BodySize;
import com.tomkasp.fitnow.profile.domain.BodySizeHistory;
import com.tomkasp.fitnow.profile.dto.BodySizeHistoryDTO;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki (A042191)
 */

@Component
public class BodySizeHistoryFactory implements DtoFactory<BodySizeHistoryDTO, BodySizeHistory>{


    @Override
    public BodySizeHistory toEntity(BodySizeHistoryDTO bodySizeHistoryDTO) {
        return null;
    }

    @Override
    public BodySizeHistoryDTO toDto(BodySizeHistory bodySize) {
        return new BodySizeHistoryDTO()
            .setArm(bodySize.getArm())
            .setChest(bodySize.getChest())
            .setHip(bodySize.getHip())
            .setId(bodySize.getId())
            .setNeck(bodySize.getNeck())
            .setThigh(bodySize.getThigh())
            .setWaist(bodySize.getWaist())
            .setDate(bodySize.getDate().toLocalDate());

    }
}

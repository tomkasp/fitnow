package com.tomkasp.fitnow.profile;

import com.tomkasp.fitnow.profile.domain.BodySize;
import com.tomkasp.fitnow.profile.dto.BodySizeDTO;
import org.mapstruct.Mapper;

/**
 * @author Tomasz Kasprzycki
 */
@Mapper(componentModel = "spring", uses = {})
public interface BodySizeMapper {

    BodySizeDTO bodySizeToBodySizeDTO(BodySize bodySize);

    BodySize bodySizeDTOToBodySize(BodySizeDTO bodySizeDTO);

}

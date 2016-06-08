package com.tomkasp.profile;

import com.tomkasp.profile.domain.BodySize;
import com.tomkasp.profile.dto.BodySizeDTO;
import org.mapstruct.Mapper;

/**
 * @author Tomasz Kasprzycki
 */
@Mapper(componentModel = "spring", uses = {})
public interface BodySizeMapper {

    BodySizeDTO bodySizeToBodySizeDTO(BodySize bodySize);

    BodySize bodySizeDTOToBodySize(BodySizeDTO bodySizeDTO);

}

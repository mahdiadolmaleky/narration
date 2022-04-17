package com.hit.narration.service.mapper;

import com.hit.narration.domain.Purpose;
import com.hit.narration.service.dto.PurposeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PurposeMapper extends EntityMapper<PurposeDTO, Purpose> {

    Purpose toEntity(PurposeDTO purposeDTO);

    default Purpose fromId(Long id) {
        if (id == null) {
            return null;
        }
        Purpose purpose = new Purpose();
        purpose.setId(id);
        return purpose;
    }
}

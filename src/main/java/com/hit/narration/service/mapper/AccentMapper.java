package com.hit.narration.service.mapper;

import com.hit.narration.domain.Accent;
import com.hit.narration.service.dto.AccentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LanguageMapper.class,NarratorMapper.class})
public interface AccentMapper extends EntityMapper<AccentDTO, Accent> {

    @Mapping(source = "language.id", target = "languageId")
    AccentDTO toDto(Accent accent);

    @Mapping(source = "languageId", target = "language")
    Accent toEntity(AccentDTO accentDTO);

    default Accent fromId(Long id) {
        if (id == null) {
            return null;
        }
        Accent accent = new Accent();
        accent.setId(id);
        return accent;
    }
}

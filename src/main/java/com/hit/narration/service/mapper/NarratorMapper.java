package com.hit.narration.service.mapper;

import com.hit.narration.domain.Narrator;
import com.hit.narration.service.dto.NarratorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CityMapper.class, AccentMapper.class, SocialMediaMapper.class,PersonMapper.class})
public interface NarratorMapper extends EntityMapper<NarratorDTO, Narrator> {

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "person.id", target = "personId")
    NarratorDTO toDto(Narrator narrator);

    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "personId", target = "person")
    Narrator toEntity(NarratorDTO narratorDTO);

    default Narrator fromId(Long id) {
        if (id == null) {
            return null;
        }
        Narrator narrator = new Narrator();
        narrator.setId(id);
        return narrator;
    }
}

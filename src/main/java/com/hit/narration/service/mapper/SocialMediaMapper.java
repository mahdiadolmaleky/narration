package com.hit.narration.service.mapper;

import com.hit.narration.domain.SocialMedia;
import com.hit.narration.service.dto.SocialMediaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {NarratorMapper.class})
public interface SocialMediaMapper extends EntityMapper<SocialMediaDTO, SocialMedia> {

    @Mapping(source = "narrator.id", target = "narratorId")
    SocialMediaDTO toDto(SocialMedia socialMedia);

    @Mapping(source = "narratorId", target = "narrator")
    SocialMedia toEntity(SocialMediaDTO socialMediaDTO);

    default SocialMedia fromId(Long id) {
        if (id == null) {
            return null;
        }
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setId(id);
        return socialMedia;
    }
}

package com.hit.narration.service.mapper;

import com.hit.narration.domain.NarrationProject;
import com.hit.narration.service.dto.NarrationProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PurposeMapper.class, AccentMapper.class, TunMapper.class,NarratorMapper.class})
public interface NarrationProjectMapper extends EntityMapper<NarrationProjectDTO, NarrationProject> {

    @Mapping(source = "purpose.id", target = "purposeId")
    @Mapping(source = "tun.id", target = "tunId")
    @Mapping(source = "accent.id", target = "accentId")
    @Mapping(source = "narrator.id", target = "narratorId")
    NarrationProjectDTO toDto(NarrationProject narrationProject);

    @Mapping(source = "purposeId", target = "purpose")
    @Mapping(source = "tunId", target = "tun")
    @Mapping(source = "accentId", target = "accent")
    @Mapping(source = "narratorId", target = "narrator")
    NarrationProject toEntity(NarrationProjectDTO narrationProjectDTO);

    default NarrationProject fromId(Long id) {
        if (id == null) {
            return null;
        }
        NarrationProject narrationProject = new NarrationProject();
        narrationProject.setId(id);
        return narrationProject;
    }
}

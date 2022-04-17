package com.hit.narration.service.mapper;

import com.hit.narration.domain.Voice;
import com.hit.narration.service.dto.VoiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PurposeMapper.class, AccentMapper.class, TunMapper.class,NarratorMapper.class,AttachmentMapper.class})
public interface VoiceMapper extends EntityMapper<VoiceDTO, Voice> {

    @Mapping(source = "tun.id", target = "tunId")
    @Mapping(source = "accent.id", target = "accentId")
    @Mapping(source = "purpose.id", target = "purposeId")
    @Mapping(source = "narrator.id", target = "narratorId")
    @Mapping(source = "audioFile.id", target = "audioFileId")
    VoiceDTO toDto(Voice voice);

    @Mapping(source = "tunId", target = "tun")
    @Mapping(source = "accentId", target = "accent")
    @Mapping(source = "purposeId", target = "purpose")
    @Mapping(source = "narratorId", target = "narrator")
    Voice toEntity(VoiceDTO voiceDTO);

    default Voice fromId(Long id) {
        if (id == null) {
            return null;
        }
        Voice voice = new Voice();
        voice.setId(id);
        return voice;
    }
}

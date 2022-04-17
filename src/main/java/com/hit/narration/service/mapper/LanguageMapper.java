package com.hit.narration.service.mapper;

import com.hit.narration.domain.Language;
import com.hit.narration.service.dto.LanguageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface LanguageMapper extends EntityMapper<LanguageDTO, Language> {

    default Language fromId(Long id) {
        if (id == null) {
            return null;
        }
        Language language = new Language();
        language.setId(id);
        return language;
    }
}

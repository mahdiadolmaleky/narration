package com.hit.narration.service.mapper;

import com.hit.narration.domain.Province;
import com.hit.narration.service.dto.ProvinceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface ProvinceMapper extends EntityMapper<ProvinceDTO, Province> {

    @Mapping(source = "country.id", target = "countryId")
    ProvinceDTO toDto(Province province);

    @Mapping(source = "countryId", target = "country")
    Province toEntity(ProvinceDTO provinceDTO);

    default Province fromId(Long id) {
        if (id == null) {
            return null;
        }
        Province province = new Province();
        province.setId(id);
        return province;
    }
}

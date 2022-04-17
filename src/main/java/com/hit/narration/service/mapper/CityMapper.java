package com.hit.narration.service.mapper;


import com.hit.narration.domain.City;
import com.hit.narration.service.dto.CityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProvinceMapper.class, AccentMapper.class, RegionMapper.class})
public interface CityMapper extends EntityMapper<CityDTO, City> {

    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "accent.id", target = "accentId")
    @Mapping(source = "region.id", target = "regionId")
    CityDTO toDto(City city);

    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "accentId", target = "accent")
    @Mapping(source = "regionId", target = "region")
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}

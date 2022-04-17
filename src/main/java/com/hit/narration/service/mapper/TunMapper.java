package com.hit.narration.service.mapper;

import com.hit.narration.domain.Tun;
import com.hit.narration.service.dto.TunDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface TunMapper extends EntityMapper<TunDTO, Tun> {

    default Tun fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tun tun = new Tun();
        tun.setId(id);
        return tun;
    }
}

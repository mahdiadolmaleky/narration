package com.hit.narration.service.mapper;

import com.hit.narration.domain.Person;
import com.hit.narration.service.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "user.id", target = "userId")
    PersonDTO toDto(Person person);

    @Mapping(source = "userId", target = "user")
    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }

}

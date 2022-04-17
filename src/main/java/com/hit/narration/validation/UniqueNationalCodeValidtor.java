package com.hit.narration.validation;

import com.hit.narration.repository.PersonRepository;
import com.hit.narration.service.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNationalCodeValidtor implements ConstraintValidator<UniqueNationalCode, PersonDTO> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void initialize(UniqueNationalCode constraintAnnotation) {

    }
    @Override
    public boolean isValid(PersonDTO personDto, ConstraintValidatorContext constraintValidatorContext) {
        if (personDto != null && !StringUtils.isEmpty(personDto.getNationalCode())) {
            if ((personDto.getId() == null && personRepository.existsPersonByNationalCode(personDto.getNationalCode())) ||
                (personDto.getId() != null && personRepository.existsPersonByNationalCodeAndIdNot(personDto.getNationalCode(), personDto.getId())))
            {
                constraintValidatorContext.buildConstraintViolationWithTemplate("")
                    .addPropertyNode("nationalCode").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}

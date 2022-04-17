package com.hit.narration.repository;

import com.hit.narration.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Set;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByNationalCode(String code);

    boolean existsPersonByNationalCodeAndIdNot(String nationalCode, Long id);

    boolean existsPersonByNationalCode(String nationalCode);
}

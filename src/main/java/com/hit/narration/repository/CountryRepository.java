package com.hit.narration.repository;

import com.hit.narration.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}

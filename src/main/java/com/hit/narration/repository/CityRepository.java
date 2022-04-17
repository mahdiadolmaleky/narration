package com.hit.narration.repository;

import com.hit.narration.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unused")
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}

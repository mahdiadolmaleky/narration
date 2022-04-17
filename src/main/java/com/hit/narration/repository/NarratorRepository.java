package com.hit.narration.repository;

import com.hit.narration.domain.Narrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface NarratorRepository extends JpaRepository<Narrator, Long> {
}

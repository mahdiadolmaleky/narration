package com.hit.narration.repository;

import com.hit.narration.domain.Accent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface AccentRepository extends JpaRepository<Accent, Long> {
}

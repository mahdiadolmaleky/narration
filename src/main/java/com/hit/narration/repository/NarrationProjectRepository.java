package com.hit.narration.repository;

import com.hit.narration.domain.NarrationProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NarrationProjectRepository extends JpaRepository<NarrationProject, Long> {
}

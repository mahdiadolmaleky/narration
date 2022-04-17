package com.hit.narration.repository;

import com.hit.narration.domain.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unused")
@Repository
public interface VoiceRepository extends JpaRepository<Voice, Long> {
}

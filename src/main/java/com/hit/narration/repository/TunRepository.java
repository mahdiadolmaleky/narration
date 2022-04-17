package com.hit.narration.repository;

import com.hit.narration.domain.Tun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unused")
@Repository
public interface TunRepository extends JpaRepository<Tun, Long> {
}

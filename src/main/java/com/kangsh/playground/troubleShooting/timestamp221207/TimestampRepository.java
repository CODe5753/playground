package com.kangsh.playground.troubleShooting.timestamp221207;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimestampRepository extends JpaRepository<TimestampEntity, Long> {

}

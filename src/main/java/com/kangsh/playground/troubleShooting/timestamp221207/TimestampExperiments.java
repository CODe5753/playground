package com.kangsh.playground.troubleShooting.timestamp221207;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimestampExperiments {

    private final TimestampRepository timestampRepository;

    public TimestampExperiments(TimestampRepository timestampRepository) {
        this.timestampRepository = timestampRepository;
    }

    @Transactional
    public TimestampEntity convertSeconds(Long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            TimeZone.getDefault().toZoneId()
        );
        TimestampEntity timestampEntity = timestampRepository.save(TimestampEntity.builder()
            .id(UUID.randomUUID().toString())
            .localDateTime(localDateTime)
            .build());
        return timestampEntity;
    }

    @Transactional
    public TimestampEntity convertMillis(Long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestamp),
            TimeZone.getDefault().toZoneId()
        );
        TimestampEntity timestampEntity = timestampRepository.save(TimestampEntity.builder()
            .id(UUID.randomUUID().toString())
            .localDateTime(localDateTime)
            .build());
        return timestampEntity;
    }
}

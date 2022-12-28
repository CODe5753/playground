package com.kangsh.playground.troubleShooting.timestamp221207;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
class TimestampExperimentsTest {

    @Autowired
    TimestampExperiments timestampExperiments;

    @Test
    @DisplayName("Incorrect datetime value 에러 발생")
    public void test_error() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            LocalDateTime localDateTime = timestampExperiments.convertSeconds(1671986736000L).getLocalDateTime();
            System.out.println("localDateTime = " + localDateTime);
//        Assertions.assertTrue(LocalDateTime.of(54953, 02, 24, 17, 00, 00).equals(localDateTime));
        });
    }

    @Test
    @DisplayName("정상적인 수행")
    public void test_success() {
        LocalDateTime localDateTime = timestampExperiments.convertMillis(1671986736000L).getLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);
        Assertions.assertTrue(LocalDateTime.of(2022, 12, 26, 01, 45, 36).equals(localDateTime));
    }
}
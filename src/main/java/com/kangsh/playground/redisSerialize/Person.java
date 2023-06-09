package com.kangsh.playground.redisSerialize;

import com.kangsh.PersonDto2;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    void setName(String name) {
        this.name = name;
    }

    PersonDto toDto() {
        return PersonDto.builder()
            .id(this.id)
            .name(this.name)
            .build();
    }

    PersonDto2 toDto2() {
        return PersonDto2.builder()
            .id(this.id)
            .name(this.name)
            .build();
    }
}

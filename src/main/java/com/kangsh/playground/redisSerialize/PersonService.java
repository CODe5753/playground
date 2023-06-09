package com.kangsh.playground.redisSerialize;

import com.kangsh.PersonDto2;
import com.kangsh.playground.config.RedisService;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final RedisService redisService;

    @Transactional(readOnly = false)
    public void createPerson(String name) {
        Person person = new Person();
        person.setName(name);
        personRepository.save(person);
        redisService.addCache(name, person.toDto());
    }

    public PersonDto getPerson(String name) {
        PersonDto person = redisService.getCache(name, PersonDto.class);
        if(Objects.isNull(person)) {
            Optional<Person> optionalPerson = personRepository.findByName(name);
            if(!optionalPerson.isPresent()) {
                throw new RuntimeException();
            }
            log.info("db를 통해 조회됨");
            return optionalPerson.get().toDto();
        }
        log.info("redis를 통해 조회됨");
        return person;
    }

    public PersonDto2 getPerson2(String name) {
        PersonDto2 person = redisService.getCache(name, PersonDto2.class);
        if(Objects.isNull(person)) {
            Optional<Person> optionalPerson = personRepository.findByName(name);
            if(!optionalPerson.isPresent()) {
                throw new RuntimeException();
            }
            log.info("db를 통해 조회됨");
            return optionalPerson.get().toDto2();
        }
        log.info("redis를 통해 조회됨");
        return person;
    }
}

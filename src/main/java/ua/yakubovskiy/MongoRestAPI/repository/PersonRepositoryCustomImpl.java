package ua.yakubovskiy.MongoRestAPI.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PersonRequestDetailsDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Person> searchByFullName(PersonRequestDetailsDto query) {
        return null;
    }
}

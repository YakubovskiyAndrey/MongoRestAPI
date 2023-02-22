package ua.yakubovskiy.MongoRestAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.yakubovskiy.MongoRestAPI.data.Person;

public interface PersonRepository extends MongoRepository<Person, String>, PersonRepositoryCustom {
}

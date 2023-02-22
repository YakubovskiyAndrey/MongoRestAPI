package ua.yakubovskiy.MongoRestAPI.repository;

import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PersonRequestDetailsDto;

import java.util.List;

public interface PersonRepositoryCustom {

    List<Person> searchByFullName(PersonRequestDetailsDto query);

}

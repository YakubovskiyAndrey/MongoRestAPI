package ua.yakubovskiy.MongoRestAPI.repository;

import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PopularNameDto;
import ua.yakubovskiy.MongoRestAPI.dto.RequestPersonDetailsDto;

import java.util.List;

public interface PersonRepositoryCustom {

    List<Person> searchByFullName(RequestPersonDetailsDto query);

    List<PopularNameDto> searchPopularNames(int searchQuantity);

}

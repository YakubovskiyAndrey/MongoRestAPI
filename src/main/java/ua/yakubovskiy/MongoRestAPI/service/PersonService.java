package ua.yakubovskiy.MongoRestAPI.service;

import org.springframework.web.multipart.MultipartFile;
import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PersonDetailsDto;
import ua.yakubovskiy.MongoRestAPI.dto.PopularNameDto;
import ua.yakubovskiy.MongoRestAPI.dto.RequestPersonDetailsDto;

import java.util.List;

public interface PersonService {

    void uploadFile(MultipartFile file);

    void createPerson(Person person);

    void deleteAll();

    List<PersonDetailsDto> searchByFullName(RequestPersonDetailsDto query);

    List<PopularNameDto> searchPopularNames(int searchQuantity);
}

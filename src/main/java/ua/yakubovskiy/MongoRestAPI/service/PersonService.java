package ua.yakubovskiy.MongoRestAPI.service;

import org.springframework.web.multipart.MultipartFile;
import ua.yakubovskiy.MongoRestAPI.data.Person;

public interface PersonService {

    void uploadFile(MultipartFile file);

    void createPerson(Person person);

    void deleteAll();
}

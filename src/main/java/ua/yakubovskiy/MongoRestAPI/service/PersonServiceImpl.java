package ua.yakubovskiy.MongoRestAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PersonSaveDto;
import ua.yakubovskiy.MongoRestAPI.repository.PersonRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public void uploadFile(MultipartFile file) {
        deleteAll();
        try(ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            if (zipEntry != null && !zipEntry.isDirectory()) {
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);

                CollectionType javaType = jsonMapper.getTypeFactory()
                        .constructCollectionType(List.class, PersonSaveDto.class);

                List<PersonSaveDto> personSaveDtoList = jsonMapper.readValue(reader, javaType);
                personSaveDtoList.forEach(personSaveDto -> {
                    Person person = new Person();
                    updateDataFromDto(person, personSaveDto);
                    createPerson(person);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDataFromDto(Person data, PersonSaveDto dto) {
        data.setFirstName(dto.getFirstName());
        data.setLastName(dto.getLastName());
        data.setDateOfBirth(dto.getDateOfBirth());
        data.setLastJobTitle(dto.getLastJobTitle());
        data.setId(dto.getId());
        data.setPatronymic(dto.getPatronymic());
        data.setLastWorkPlace(dto.getLastWorkplace());
        data.setDeclarations(dto.getDeclarations());
        data.setPep(dto.isPep());
    }

    @Override
    public void createPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }
}

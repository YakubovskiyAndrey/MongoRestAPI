package ua.yakubovskiy.MongoRestAPI.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PersonSaveDto;
import ua.yakubovskiy.MongoRestAPI.dto.RequestPersonDetailsDto;
import ua.yakubovskiy.MongoRestAPI.dto.PersonDetailsDto;
import ua.yakubovskiy.MongoRestAPI.dto.PopularNameDto;
import ua.yakubovskiy.MongoRestAPI.exception.FailedDownloadException;
import ua.yakubovskiy.MongoRestAPI.repository.PersonRepository;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public void uploadFile(MultipartFile file) {
        deleteAll();
        File fileTmp;
        try {
            fileTmp = File.createTempFile(file.getName(), ".zip");
            file.transferTo(fileTmp);
        } catch (IOException e) {
            throw new FailedDownloadException("Failed to create temp file. " + e.getMessage());
        }

        try (ZipFile zipFile = new ZipFile(fileTmp)){
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry != null && !entry.isDirectory()) {
                    try(InputStream in = zipFile.getInputStream(entry)) {
                        parsePep(in);
                    }
                }
            }
        } catch (IOException e) {
            throw new FailedDownloadException("Failed to load data. " + e.getMessage());
        }
        fileTmp.deleteOnExit();
    }

    private void parsePep(InputStream in){
        try (JsonParser jsonParser = OBJECT_MAPPER.getFactory().createParser(in)){

            JavaType javaType = OBJECT_MAPPER.getTypeFactory()
                    .constructType(PersonSaveDto.class);

            if (jsonParser.nextToken() == JsonToken.START_ARRAY) {
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    PersonSaveDto dto = OBJECT_MAPPER.readValue(jsonParser, javaType);
                    Person person = new Person();
                    updateDataFromDto(person, dto);
                    createPerson(person);
                }
            }
        } catch (IOException e) {
            throw new FailedDownloadException("Failed to parse data. " + e.getMessage());
        }
    }

    private void updateDataFromDto(Person data, PersonSaveDto dto) {
        data.setFirstName(dto.getFirstName());
        data.setLastName(dto.getLastName());
        data.setDateOfBirth(dto.getDateOfBirth());
        data.setLastJobTitle(dto.getLastJobTitle());
        data.setPatronymic(dto.getPatronymic());
        data.setLastWorkPlace(dto.getLastWorkplace());
        data.setDeclarations(dto.getDeclarations());
        data.setRelatedCompanies(dto.getRelatedCompanies());
        data.setRelatedPeople(dto.getRelatedPersons());
        data.setPep(dto.isPep());
        data.setPatronymicEn(dto.getPatronymicEn());
        data.setUrl(dto.getUrl());
        data.setAlsoKnownAsEn(dto.getAlsoKnownAsEn());
        data.setAlsoKnownAsUk(dto.getAlsoKnownAsUk());
        data.setCityOfBirthEn(dto.getCityOfBirthEn());
        data.setCityOfBirthUk(dto.getCityOfBirthUk());
        data.setDied(dto.isDied());
        data.setFirstNameEn(dto.getFirstNameEn());
        data.setLastNameEn(dto.getLastNameEn());
        data.setFullNameEn(dto.getFullNameEn());
        data.setFullName(dto.getFullName());
        data.setLastWorkplaceEn(dto.getLastWorkplaceEn());
        data.setNames(dto.getNames());
        data.setPhoto(dto.getPhoto());
        data.setReasonOfTermination(dto.getReasonOfTermination());
        data.setReasonOfTerminationEn(dto.getReasonOfTerminationEn());
        data.setReputationAssetsEn(dto.getReputationAssetsEn());
        data.setReputationAssetsUk(dto.getReputationAssetsUk());
        data.setReputationConvictionsEn(dto.getReputationConvictionsEn());
        data.setReputationConvictionsUk(dto.getReputationConvictionsUk());
        data.setReputationManhuntEn(dto.getReputationManhuntEn());
        data.setReputationManhuntUk(dto.getReputationManhuntUk());
        data.setReputationSanctionsEn(dto.getReputationSanctionsEn());
        data.setReputationSanctionsUk(dto.getReputationSanctionsUk());
        data.setReputationCrimesEn(dto.getReputationCrimesEn());
        data.setReputationCrimesUk(dto.getReputationCrimesUk());
        data.setLastJobTitleEn(dto.getLastJobTitleEn());
        data.setTerminationDateHuman(dto.getTerminationDateHuman());
        data.setWikiEn(dto.getWikiEn());
        data.setWikiUk(dto.getWikiUk());
        data.setTypeOfOfficialEn(dto.getTypeOfOfficialEn());
        data.setTypeOfOfficial(dto.getTypeOfOfficial());
    }

    @Override
    public void createPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }

    @Override
    @Transactional
    public List<PersonDetailsDto> searchByFullName(RequestPersonDetailsDto query) {
        List<Person> people = personRepository.searchByFullName(query);
        List<PersonDetailsDto> details = new ArrayList<>();
        people.forEach(person -> details.add(convertToDetails(person)));
        return details;
    }

    @Override
    @Transactional
    public List<PopularNameDto> searchPopularNames(int searchQuantity) {
        return personRepository.searchPopularNames(searchQuantity);
    }

    private PersonDetailsDto convertToDetails(Person data) {
        return PersonDetailsDto.builder()
                .dateOfBirth(data.getDateOfBirth())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .patronymic(data.getPatronymic())
                .isPep(data.isPep())
                .lastJobTitle(data.getLastJobTitle())
                .lastWorkPlace(data.getLastWorkPlace())
                .build();
    }
}

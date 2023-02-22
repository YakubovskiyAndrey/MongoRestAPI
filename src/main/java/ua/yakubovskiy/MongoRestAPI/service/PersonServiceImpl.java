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
import ua.yakubovskiy.MongoRestAPI.exception.FailedDownloadException;
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
            new FailedDownloadException("Failed to load data");
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
}

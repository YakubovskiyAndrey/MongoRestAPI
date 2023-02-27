package ua.yakubovskiy.MongoRestAPI.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import ua.yakubovskiy.MongoRestAPI.TestUtil;
import ua.yakubovskiy.MongoRestAPI.MongoRestApiApplication;
import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PersonDetailsDto;
import ua.yakubovskiy.MongoRestAPI.dto.RestResponse;
import ua.yakubovskiy.MongoRestAPI.repository.PersonRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MongoRestApiApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PersonRepository repository;

    @Value("classpath:test_pep.zip")
    Resource fileResource;

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @Test
    void testUploadFile() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile(
                "file",
                "test_pep.zip",
                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                fileResource.getInputStream());

        MockHttpServletResponse response = mvc.perform(multipart("/api/persons/upload")
                        .file(firstFile))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        RestResponse result = TestUtil.parseJson(response.getContentAsString(), RestResponse.class);
        assertThat(result.result()).isEqualTo("Public persons have been successfully uploaded to database");

        List<Person> personList = repository.findAll();
        assertThat(personList).isNotEmpty();
        assertThat(personList.get(0).getFirstName()).isEqualTo("Єгор");
    }

    @Test
    void testSearchByFullName() throws Exception {

        String firstName = "Ivan";
        String lastName = "Ivanov";
        String patronymic = "Ivanovich";

        String body = """
          {
              "firstName": "%s",
              "lastName": "%s",
              "patronymic": "%s"
          }          
        """.formatted(firstName, lastName, patronymic);

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPatronymic(patronymic);
        repository.save(person);

        MockHttpServletResponse response = mvc.perform(get("/api/persons/_searchByFullName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();


        List<PersonDetailsDto> resultDtoList = TestUtil.parseJson(response.getContentAsString(), new TypeReference<>() {});

        assertThat(resultDtoList.get(0).getFirstName()).isEqualTo(firstName);
        assertThat(resultDtoList.get(0).getLastName()).isEqualTo(lastName);
        assertThat(resultDtoList.get(0).getPatronymic()).isEqualTo(patronymic);
    }

}
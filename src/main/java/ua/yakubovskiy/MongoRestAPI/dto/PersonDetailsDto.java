package ua.yakubovskiy.MongoRestAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDetailsDto {

    private String firstName;

    private String lastName;

    private String patronymic;

    private String lastJobTitle;

    private String lastWorkPlace;

    private String dateOfBirth;

    private boolean isPep;

}

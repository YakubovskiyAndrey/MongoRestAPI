package ua.yakubovskiy.MongoRestAPI.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonDetailsDto {

    private String firstName;

    private String lastName;

    private String patronymic;

    private String lastJobTitle;

    private String lastWorkPlace;

    private String dateOfBirth;

    private boolean isPep;

}

package ua.yakubovskiy.MongoRestAPI.dto;

import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
public class PopularNameDto {

    private String firstName;

    private int count;

}

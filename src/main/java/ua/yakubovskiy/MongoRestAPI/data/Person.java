package ua.yakubovskiy.MongoRestAPI.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@FieldNameConstants
@Document("people")
public class Person {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String lastJobTitle;

    private String lastWorkPlace;

    private String dateOfBirth;

    private List<Declaration> declarations;

    private boolean isPep;
}

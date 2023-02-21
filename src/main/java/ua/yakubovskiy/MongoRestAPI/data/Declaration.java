package ua.yakubovskiy.MongoRestAPI.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Setter;
import ua.yakubovskiy.MongoRestAPI.deserializer.DeclarationDeserializer;

@JsonDeserialize(using = DeclarationDeserializer.class)
@Setter
public class Declaration {

    private String positionEn;

    private String url;

    private double income;

    private String regionUk;

    private String officeEn;

    private String positionUk;

    private String year;

    private String officeUk;

    private String regionEn;

    private double familyIncome;

}

package ua.yakubovskiy.MongoRestAPI.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Setter;
import ua.yakubovskiy.MongoRestAPI.deserializer.RelatedPersonDeserializer;

@Setter
@JsonDeserialize(using = RelatedPersonDeserializer.class)
public class RelatedPerson {

    private String relationshipTypeEn;

    private String dateEstablished;

    private String personEn;

    private String dateConfirmed;

    private boolean isPep;

    private String dateFinished;

    private String personUk;

    private String relationshipType;
}

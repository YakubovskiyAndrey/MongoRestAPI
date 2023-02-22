package ua.yakubovskiy.MongoRestAPI.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Setter;
import ua.yakubovskiy.MongoRestAPI.deserializer.RelatedCompanyDeserializer;

@Setter
@JsonDeserialize(using = RelatedCompanyDeserializer.class)
public class RelatedCompany {

    private String relationshipTypeEn;

    private String toCompanyShortEn;

    private String dateEstablished;

    private String toCompanyEdrpou;

    private String toCompanyFounded;

    private String dateFinished;

    private boolean toCompanyIsState;

    private String dateConfirmed;

    private String toCompanyUk;

    private String toCompanyShortUk;

    private String toCompanyEn;

    private String relationshipTypeUk;

}

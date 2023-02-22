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

    private List<RelatedCompany> relatedCompanies;

    private List<RelatedPerson> relatedPeople;

    private boolean isPep;

    private String wikiUk;

    private String photo;

    private String reputationConvictionsEn;

    private String firstNameEn;

    private String lastWorkplaceEn;

    private String names;

    private String fullName;

    private String alsoKnownAsEn;

    private String reputationManhuntUk;

    private String reputationSanctionsUk;

    private String patronymicEn;

    private String reasonOfTerminationEn;

    private String reputationAssetsEn;

    private String reputationConvictionsUk;

    private String reputationCrimesEn;

    private String reasonOfTermination;

    private String fullNameEn;

    private String cityOfBirthUk;

    private String typeOfOfficial;

    private boolean died;

    private String lastNameEn;

    private String lastJobTitleEn;

    private String reputationManhuntEn;

    private String alsoKnownAsUk;

    private String url;

    private String terminationDateHuman;

    private String lastWorkplace;

    private String cityOfBirthEn;

    private String reputationSanctionsEn;

    private String reputationCrimesUk;

    private String wikiEn;

    private String reputationAssetsUk;

    private String typeOfOfficialEn;
}

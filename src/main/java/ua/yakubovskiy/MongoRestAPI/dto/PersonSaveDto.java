package ua.yakubovskiy.MongoRestAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import ua.yakubovskiy.MongoRestAPI.data.Declaration;
import ua.yakubovskiy.MongoRestAPI.data.RelatedCompany;
import ua.yakubovskiy.MongoRestAPI.data.RelatedPerson;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonSaveDto {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("last_job_title")
    private String lastJobTitle;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("wiki_uk")
    private String wikiUk;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("reputation_convictions_en")
    private String reputationConvictionsEn;

    @JsonProperty("first_name_en")
    private String firstNameEn;

    @JsonProperty("last_workplace_en")
    private String lastWorkplaceEn;

    @JsonProperty("names")
    private String names;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("also_known_as_en")
    private String alsoKnownAsEn;

    @JsonProperty("reputation_manhunt_uk")
    private String reputationManhuntUk;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("declarations")
    private List<Declaration> declarations;

    @JsonProperty("reputation_sanctions_uk")
    private String reputationSanctionsUk;

   // @JsonProperty("related_companies")
    private List<RelatedCompany> relatedCompanies;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("patronymic_en")
    private String patronymicEn;

    @JsonProperty("reason_of_termination_en")
    private String reasonOfTerminationEn;

    @JsonProperty("reputation_assets_en")
    private String reputationAssetsEn;

   // @JsonProperty("related_persons")
    private List<RelatedPerson> relatedPersons;

    @JsonProperty("reputation_convictions_uk")
    private String reputationConvictionsUk;

    @JsonProperty("reputation_crimes_en")
    private String reputationCrimesEn;

    @JsonProperty("reason_of_termination")
    private String reasonOfTermination;

    @JsonProperty("full_name_en")
    private String fullNameEn;

    @JsonProperty("city_of_birth")
    private String cityOfBirthUk;

    @JsonProperty("type_of_official")
    private String typeOfOfficial;

    @JsonProperty("died")
    private boolean died;

    @JsonProperty("last_name_en")
    private String lastNameEn;

    @JsonProperty("last_job_title_en")
    private String lastJobTitleEn;

    @JsonProperty("is_pep")
    private boolean isPep;

    @JsonProperty("reputation_manhunt_en")
    private String reputationManhuntEn;

    @JsonProperty("also_known_as_uk")
    private String alsoKnownAsUk;

    @JsonProperty("url")
    private String url;

    @JsonProperty("termination_date_human")
    private String terminationDateHuman;

    @JsonProperty("last_workplace")
    private String lastWorkplace;

    @JsonProperty("city_of_birth_en")
    private String cityOfBirthEn;

    @JsonProperty("reputation_sanctions_en")
    private String reputationSanctionsEn;

    @JsonProperty("reputation_crimes_uk")
    private String reputationCrimesUk;

    @JsonProperty("wiki_en")
    private String wikiEn;

    @JsonProperty("reputation_assets_uk")
    private String reputationAssetsUk;

    @JsonProperty("type_of_official_en")
    private String typeOfOfficialEn;
}

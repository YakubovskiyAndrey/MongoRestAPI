package ua.yakubovskiy.MongoRestAPI.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ua.yakubovskiy.MongoRestAPI.data.RelatedCompany;

import java.io.IOException;

public class RelatedCompanyDeserializer extends JsonDeserializer<RelatedCompany> {
    @Override
    public RelatedCompany deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        RelatedCompany relatedCompany = new RelatedCompany();

        relatedCompany.setToCompanyEn(node.get("to_company_en").asText());
        relatedCompany.setRelationshipTypeEn(node.get("relationship_type_en").asText());
        relatedCompany.setToCompanyShortEn(node.get("to_company_short_en").asText());
        relatedCompany.setDateEstablished(node.get("date_established").asText());
        relatedCompany.setToCompanyEdrpou(node.get("to_company_edrpou").asText());
        relatedCompany.setToCompanyFounded(node.get("to_company_founded").asText());
        relatedCompany.setDateFinished(node.get("date_finished").asText());
        relatedCompany.setToCompanyIsState(node.get("to_company_is_state").asBoolean());
        relatedCompany.setDateConfirmed(node.get("date_confirmed").asText());
        relatedCompany.setToCompanyUk(node.get("to_company_uk").asText());
        relatedCompany.setToCompanyShortUk(node.get("to_company_short_uk").asText());
        relatedCompany.setRelationshipTypeUk(node.get("relationship_type_uk").asText());

        return  relatedCompany;
    }
}

package ua.yakubovskiy.MongoRestAPI.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ua.yakubovskiy.MongoRestAPI.data.RelatedPerson;

import java.io.IOException;

public class RelatedPersonDeserializer extends JsonDeserializer<RelatedPerson> {
    @Override
    public RelatedPerson deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        RelatedPerson relatedPerson = new RelatedPerson();

        relatedPerson.setRelationshipTypeEn(node.get("relationship_type_en").asText());
        relatedPerson.setDateEstablished(node.get("date_established").asText());
        relatedPerson.setPersonEn(node.get("person_en").asText());
        relatedPerson.setDateConfirmed(node.get("date_confirmed").asText());
        relatedPerson.setPep(node.get("is_pep").asBoolean());
        relatedPerson.setDateFinished(node.get("date_finished").asText());
        relatedPerson.setPersonUk(node.get("person_uk").asText());
        relatedPerson.setRelationshipType(node.get("relationship_type").asText());

        return relatedPerson;
    }
}

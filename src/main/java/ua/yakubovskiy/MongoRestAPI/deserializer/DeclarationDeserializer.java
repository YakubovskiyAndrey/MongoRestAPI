package ua.yakubovskiy.MongoRestAPI.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ua.yakubovskiy.MongoRestAPI.data.Declaration;

import java.io.IOException;

public class DeclarationDeserializer extends JsonDeserializer<Declaration> {

    @Override
    public Declaration deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Declaration declaration = new Declaration();

        if(node.get("income") != null) {
            declaration.setIncome(node.get("income").asDouble());
        }
        if(node.get("family_income") != null) {
            declaration.setFamilyIncome(node.get("family_income").asDouble());
        }
        declaration.setPositionEn(node.get("position_en").asText());
        declaration.setUrl(node.get("url").asText());
        declaration.setRegionUk(node.get("region_uk").asText());
        declaration.setOfficeEn(node.get("office_en").asText());
        declaration.setPositionUk(node.get("position_uk").asText());
        declaration.setYear(node.get("year").asText());
        declaration.setOfficeUk(node.get("office_uk").asText());
        declaration.setRegionEn(node.get("region_en").asText());

        return declaration;
    }
}

package ua.yakubovskiy.MongoRestAPI.repository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.yakubovskiy.MongoRestAPI.data.Person;
import ua.yakubovskiy.MongoRestAPI.dto.PopularNameDto;
import ua.yakubovskiy.MongoRestAPI.dto.RequestPersonDetailsDto;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Person> searchByFullName(RequestPersonDetailsDto query) {
        Query mongoQuery = new Query();
        if (StringUtils.isNotBlank(query.getFirstName())) {
            mongoQuery.addCriteria(where(Person.Fields.firstName).is(query.getFirstName()));
        }
        if (StringUtils.isNotBlank(query.getLastName())) {
            mongoQuery.addCriteria(where(Person.Fields.lastName).is(query.getLastName()));
        }
        if (StringUtils.isNotBlank(query.getPatronymic())) {
            mongoQuery.addCriteria(where(Person.Fields.patronymic).is(query.getPatronymic()));
        }
        return mongoTemplate.find(mongoQuery, Person.class);
    }

    @Override
    public List<PopularNameDto> searchPopularNames(int searchQuantity) {
        AggregationOperation match = Aggregation.match(where(Person.Fields.isPep).is(true));
        AggregationOperation group = Aggregation.group(Person.Fields.firstName).count().as("count");
        AggregationOperation project = Aggregation.project("count").andExclude("_id")
                .andInclude(Fields.from(Fields.field(Person.Fields.firstName, "_id")));
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, "count");
        AggregationOperation limit = Aggregation.limit(searchQuantity);

        Aggregation aggregation = Aggregation.newAggregation(match, group, project, sort, limit);

        return mongoTemplate
                .aggregate(aggregation, "people", PopularNameDto.class).getMappedResults();
    }
}

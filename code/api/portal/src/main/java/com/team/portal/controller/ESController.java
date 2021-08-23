package com.team.portal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.cooperated.controller.BaseController;
import lombok.SneakyThrows;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "es")
public class ESController extends BaseController {

    @Autowired
    RestHighLevelClient highLevelClient;

    @Autowired
    ObjectMapper objectMapper;

    @SneakyThrows
    @PostMapping(path = "put")
    public Map<String, Object> putObj() {
        IndexRequest request = new IndexRequest("posts");
        request.id(UUID.randomUUID().toString());
        String jsonString = objectMapper.writeValueAsString(Map.of("name", "陈昭宇"));
        request.source(jsonString, XContentType.JSON);
        highLevelClient.index(request, RequestOptions.DEFAULT);
        return Map.of("status", "success");
    }

    @PostMapping(path = "agg1")
    public List<Map<String, Object>> agg1() throws IOException {

        SearchRequest searchRequest = new SearchRequest("idx_ent_sale");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder agg1 = AggregationBuilders
                .terms("group_by1")
                .field("from_institution_name.keyword");
        agg1.subAggregation(AggregationBuilders
                .count("count")
                .field("to_institution_name.keyword"));

        TermsAggregationBuilder agg2 = AggregationBuilders
                .terms("group_by2")
                .field("to_institution_name.keyword");
        agg2.subAggregation(AggregationBuilders
                .count("count")
                .field("from_institution_name.keyword"));

        searchSourceBuilder.aggregation(agg1);
        searchSourceBuilder.aggregation(agg2);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return Arrays.stream(searchResponse.getHits().getHits()).map(SearchHit::getSourceAsMap).collect(Collectors.toList());

    }


    @PostMapping(path = "agg2")
    public List<Map<String, Object>> agg2() throws IOException {

        Request request=new Request("post","_sql");
        request.setJsonEntity("{\"query\": \"select * from idx_ent_sale\"}");
        Response response=highLevelClient.getLowLevelClient().performRequest(request);
        String responseBody = EntityUtils.toString(response.getEntity());

        return List.of();

    }


}

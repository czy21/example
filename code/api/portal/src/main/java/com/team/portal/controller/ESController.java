package com.team.portal.controller;

import com.czy.learning.web.controller.BaseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "es")
public class ESController extends BaseController {

//    @Autowired
//    RestHighLevelClient highLevelClient;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @SneakyThrows
//    @PostMapping(path = "put")
//    public Map<String, Object> putObj(@RequestBody Map<String, Object> param) {
//        IndexRequest request = new IndexRequest("kf-log-token");
//        param.put("time", LocalDateTime.now(ZoneOffset.UTC).toString());
//        String jsonString = objectMapper.writeValueAsString(param);
//        request.source(jsonString, XContentType.JSON);
//        highLevelClient.index(request, RequestOptions.DEFAULT);
//        return Map.of("status", "success");
//    }
//
//    @PostMapping(path = "agg1")
//    public Map<String, Object> agg1() throws IOException {
//
//        SearchRequest searchRequest = new SearchRequest("idx_ent_sale");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        TermsAggregationBuilder agg1 = AggregationBuilders
//                .terms("group_by1")
//                .field("from_institution_name.keyword");
//
//        TermsAggregationBuilder agg2 = AggregationBuilders
//                .terms("group_by2")
//                .field("to_institution_name.keyword");
//
//        searchSourceBuilder.aggregation(agg1);
//        searchSourceBuilder.aggregation(agg2);
//        searchSourceBuilder.size(0);
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        List<Map<String, Object>> groupBy1 = ((ParsedStringTerms) searchResponse.getAggregations()
//                .asMap().get("group_by1"))
//                .getBuckets().stream()
//                .map(t -> Map.of("from_institution_name", t.getKey(), "count", t.getDocCount()))
//                .collect(Collectors.toList());
//
//        List<Map<String, Object>> groupBy2 = ((ParsedStringTerms) searchResponse.getAggregations()
//                .asMap().get("group_by2"))
//                .getBuckets().stream()
//                .map(t -> Map.of("to_institution_name", t.getKey(), "count", t.getDocCount()))
//                .collect(Collectors.toList());
//
//        return Map.of("g1", groupBy1, "g2", groupBy2);
//
//    }
//
//
//    @PostMapping(path = "agg2")
//    public List<Map<String, Object>> agg2() throws IOException {
//
//        Request request = new Request("post", "_sql");
//        request.setJsonEntity("{\"query\": \"select * from idx_ent_sale\"}");
//        Response response = highLevelClient.getLowLevelClient().performRequest(request);
//        String responseBody = EntityUtils.toString(response.getEntity());
//
//        return List.of();
//
//    }


}

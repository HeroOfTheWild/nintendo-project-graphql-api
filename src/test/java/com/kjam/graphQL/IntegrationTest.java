package com.kjam.graphQL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
 
    private static final String GRAPHQL_QUERY_REQUEST_PATH  = "graphql/queries/requests/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/queries/responses/%s.json";

    @Autowired
    private GraphQLTestTemplate testTemplate;

    private String toJsonResponse(String jsonFileLocation) throws IOException {
        return IOUtils.toString(new ClassPathResource(jsonFileLocation).getInputStream(), StandardCharsets.UTF_8);
    }

    private void verifySuccessfulGraphQLResponse(GraphQLResponse response, String expectedResponse) throws JSONException {
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedResponse, response.getRawResponse().getBody(), true);
    }

    private GraphQLResponse retrieveGraphQLResponse(String queryFileLocation) throws IOException {
        return testTemplate.postForResource(queryFileLocation);
    }

    @Test
    public void query_franchise_successfully() throws IOException, JSONException {
        var fileName = "franchise";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_franchises_successfully() throws IOException, JSONException {
        var fileName = "franchises";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_project_successfully() throws IOException, JSONException {
        var fileName = "project";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_projects_successfully() throws IOException, JSONException {
        var fileName = "projects";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_projectsByCriteria_successfully() throws IOException, JSONException {
        var fileName = "projectsByCriteria";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_projectsByCriteriaWithFranchiseId_successfully() throws IOException, JSONException {
        var fileName = "projectsByCriteria_franchiseId";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }

    @Test
    public void query_projectsByCriteriaWithFranchiseIdAndStatus_successfully() throws IOException, JSONException {
        var fileName = "projectsByCriteria_franchiseId_status";
        var response = retrieveGraphQLResponse(String.format(GRAPHQL_QUERY_REQUEST_PATH, fileName));
        var expectedResponse = toJsonResponse(String.format(GRAPHQL_QUERY_RESPONSE_PATH, fileName));
        verifySuccessfulGraphQLResponse(response, expectedResponse);
    }
}

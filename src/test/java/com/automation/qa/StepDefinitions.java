package com.automation.qa;


import com.automation.qa.util.SearchQuerySuccessResponse;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StepDefinitions {

    static {

        RestAssured.baseURI = System.getenv("url");
    }

    private static String URL_PREFIX = "/search/repositories";
    private Response response;


    @When("^User makes a search call for repositories to github with the parameters below")
    public void userMakesASearchCallForRepositories(DataTable table) {
        List<SearchQueryParameters> searchQueryParameters = table.asList(SearchQueryParameters.class);
        for (SearchQueryParameters searchQueryParameter : searchQueryParameters) {
            String q = searchQueryParameter.getQ();
            String order = searchQueryParameter.getOrder();
            String sort = searchQueryParameter.getSort();

            callSearchQueryAPI(q, order, sort);
        }
    }

    public void callSearchQueryAPI(String q, String sort, String order) {

        Map<String, Object> params = new HashMap<>();
        params.put("q", q);
        params.put("sort", sort);
        params.put("order", order);

        this.response = given().contentType("application/vnd.github.mercy-preview+json").basePath(URL_PREFIX).queryParams(params).
                when().
                get();

        Assert.assertEquals(200, response.getStatusCode());

        SearchQuerySuccessResponse searchQuerySuccessResponse = response.getBody().as(SearchQuerySuccessResponse.class);


    }

    @Then("^verify a response code of (.*) is returned")
    public void verifyAResponseCodeOfIsReturned(int resCode) {

        this.response = given().contentType("application/vnd.github.mercy-preview+json").basePath(URL_PREFIX).
                when().
                get();

        Assert.assertEquals(resCode, response.getStatusCode());


    }

    @Then("^verify the item count,total count and incomplete result (.*) in the response json")
    public void verifyTheItemCountAndTotalCount(boolean incomplete_items) {
        SearchQuerySuccessResponse searchQuerySuccessResponse = response.getBody().as(SearchQuerySuccessResponse.class);
        Assert.assertEquals(searchQuerySuccessResponse.getIncomplete_results(), incomplete_items);
        Assert.assertTrue(searchQuerySuccessResponse.getItems().size() != 0);
        Assert.assertTrue((searchQuerySuccessResponse.getTotal_count() != 0));

    }
}

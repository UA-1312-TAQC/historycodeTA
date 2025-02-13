package com.historycode.api.adminPanel.news;

import com.historycode.api.clients.NewsClient;
import com.historycode.api.models.adminPanel.news.GetAllNewsResponse;
import com.historycode.api.models.adminPanel.news.News;
import com.historycode.api.testRunners.ApiTestRunner;

import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewsTest extends ApiTestRunner {
    NewsClient client;
    @BeforeClass
    public void setUpClass(){
        client = new NewsClient(testValueProvider.getBaseAPIUrl());
    }

    @Test
    public void testGetAllNews(){
        Response response = client.getAll();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        GetAllNewsResponse getAllNewsResponse = response.body().as(GetAllNewsResponse.class);
        softAssert.assertFalse(getAllNewsResponse.getNews() == null || getAllNewsResponse.getNews().isEmpty(), "The news is not present in the response body");
        softAssert.assertAll();
    }

    @Test
    @Issue("210")
    public void testGetNewsById() {
        Response response = client.getAll();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        GetAllNewsResponse getAllNewsResponse = response.body().as(GetAllNewsResponse.class);
        softAssert.assertFalse(getAllNewsResponse.getNews() == null || getAllNewsResponse.getNews().isEmpty(), "The news is not present in the response body");

        News newsItem = getAllNewsResponse.getNews().get(0);
        int newsId = newsItem.getId();

        Response newsResponse = client.getById(newsId);

        softAssert.assertEquals(newsResponse.getStatusCode(), 200);

        News singleNews = newsResponse.body().as(News.class);
        softAssert.assertNotNull(singleNews, "The news is not present in the response body");
        softAssert.assertEquals(singleNews.getId(), newsId, "The news ID does not match the requested ID");

        softAssert.assertAll();
    }

    @Test
    @Issue("211")
    public void testGetNewsByUrlWhenUrlIsValid() {
        Response response = client.getAll();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        
        GetAllNewsResponse getAllNewsResponse = response.body().as(GetAllNewsResponse.class);
        softAssert.assertFalse(getAllNewsResponse.getNews() == null || getAllNewsResponse.getNews().isEmpty(), "The news is not present in the response body");

        News newsItem = getAllNewsResponse.getNews().get(0);
        String newsUrl = newsItem.getUrl();

        Response newsResponse = client.getByUrl(newsUrl);

        softAssert.assertEquals(newsResponse.getStatusCode(), 200);

        News singleNews = newsResponse.body().as(News.class);
        softAssert.assertNotNull(singleNews, "The news is not present in the response body");
        softAssert.assertEquals(singleNews.getUrl(), newsUrl, "The news URL does not match the requested URL");

        softAssert.assertAll();
    }
}

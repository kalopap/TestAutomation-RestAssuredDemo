package tests;

import clients.ArticleClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojos.Article;
import pojos.ArticleDetails;
import pojos.Ratings;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArticleTests {


    Article article;
    ArticleDetails details;
    Ratings ratings;

    @BeforeMethod
    public void setupData(){

        ratings = new Ratings();
        ratings.setAvgRating(4.5f);
        ratings.setTotalVotes(109);

        //article details
        details = new ArticleDetails();

        details.setActive(true);
        details.setContentType("TroubleShooting");
        details.setCreatedBy("Tester1");
        details.setDescription("This article contains the trouble shooting ways to solve Internet Issues");
        details.setLastModifiedBy("Tester1");
        details.setCreatedDate(LocalDateTime.now().toString());
        details.setLastModifiedDate(LocalDateTime.now().toString());
        details.setRelatedArticles(List.of("sample","debug"));
        details.setTags(List.of("errors","fix"));
        details.setViews(140);
        details.setRatings(ratings);

        article = new Article();
        article.setId("TS25684");
        article.setLocale("de_DE");
        article.setTitle("Trouble Shooting Article for Internet Issues");
        article.setDetails(details);

    }

    @Test
    public void verifyCreateArticle() {

        Response response = ArticleClient.createArticle(article);
        Assert.assertEquals(response.statusCode(),201);

    }

    @Test
    public void verifyGetArticle(){
        Response response = ArticleClient.getArticle("TS25684");
        System.out.println(response.jsonPath().get("details").toString());
        System.out.println(response.getBody().asString());

    }
    @Test
    public void verifyGetAllArticles(){
        Response response = ArticleClient.getAllArticles();
        System.out.println(response.jsonPath().get().toString());
    }

    @Test
    public void verifyUpdateFullArticle(){

        article.setTitle("Updated Title of the article");
        details.setViews(111);
        Response response = ArticleClient.updateArticle(article,article.getId());
        System.out.println(response.getBody().asString());
        System.out.println("Updated views -> " + response.jsonPath().getInt("details.views"));
        System.out.println("Updated Title -> " + response.jsonPath().get("title"));

    }
    @Test
    public void verifyUpdatePartialArticle(){

        Map<String, Object> ratingsMap = new HashMap<>();
        ratingsMap.put("totalVotes",124);

        Map<String,Object> detailsMap = new HashMap<>();
        detailsMap.put("description","This description is updated today");
        detailsMap.put("relatedTags",List.of("errors","fix","updated"));
        detailsMap.put("ratings",ratingsMap);

        Map<String, Object> updates = new HashMap<>();
        updates.put("title","Partial update of the article");
        updates.put("details",detailsMap);


        Response response = ArticleClient.updateArticle(article.getId(),updates);

        System.out.println(response.getBody().asString());

    }
    @Test
    public void verifyDeleteArticleTest(){
        System.out.println("Total articles BEFORE deleting -> "+ ArticleClient.getAllArticles().jsonPath().getList("articles").size());
        Response response = ArticleClient.deleteArticle(article.getId());
        System.out.println(response.getBody().asString());
        System.out.println("Total articles AFTER deleting -> "+ ArticleClient.getAllArticles().jsonPath().getList("articles").size());

    }


}

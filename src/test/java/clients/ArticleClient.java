package clients;
//contains CRUD for Article operations

import base.BaseClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import pojos.Article;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class ArticleClient extends BaseClient {

    private static final String ARTICLES_ENDPOINT = "/articles";

    //get All articles
    public static Response getAllArticles(){
        Response response =
                        given()
                                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                        .when()
                        .get(BASE_URL+ARTICLES_ENDPOINT);
        return response;

    }

    public static Response createArticle(Article payload) {

        Response response =
                        given()
                                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                                .body(payload)
                        .when()
                        .post(BaseClient.BASE_URL+ARTICLES_ENDPOINT);
        return response;
    }

    public static Response updateArticle(Article fullArticle,String articleId){
        Response response =
                        given()
                                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                                .body(fullArticle)
                        .when()
                        .put(BASE_URL+ARTICLES_ENDPOINT+"/"+articleId);
        return response;

    }
    public static Response updateArticle(String articleId, Map<String,Object> updates){
        Response response =
                given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(updates)
                .when()
                    .patch(BASE_URL+ARTICLES_ENDPOINT+"/"+articleId);
        return response;
    }

    public static Response getArticle(String articleId){
        Response response =
                 given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                .when()
                .get(BASE_URL+ARTICLES_ENDPOINT+"/"+articleId);
        return response;
    }

    public static Response deleteArticle(String articleId){
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                .when()
                .delete(BASE_URL+ARTICLES_ENDPOINT+"/"+articleId);
        return response;
    }
}

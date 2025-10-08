package tests;

import clients.ArticleClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojos.App;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthTestsForArticles {

    List<App> apps;

    @BeforeClass
    public void loadApps() throws IOException {

        apps = new ArrayList<>();
        File file = new File(System.getProperty("user.dir")+"/src/resources/testdata/appId.json");
        ObjectMapper mapper = new ObjectMapper();
        apps = Arrays.asList(mapper.readValue(file, App[].class));
        System.out.println("Total apps ->" + apps.size());
    }

    @Test
    public void testArticleAccess(){

        for (App app : apps){
            Response response = ArticleClient.getAllArticlesWithAuth(app.getToken());
            System.out.println("AppId: " + app.getAppId() + " , App Name: "+ app.getName());
            System.out.println(response.body().asString());
        }
    }
}

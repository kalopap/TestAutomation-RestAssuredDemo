package base;

/*
Urls:

 */

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseClient {

    public static String BASE_URL = "http://localhost:3000";

    protected RequestSpecification getRequestSpec(){
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .header("Content-Type","application/json")
                .log().all();
    }

}

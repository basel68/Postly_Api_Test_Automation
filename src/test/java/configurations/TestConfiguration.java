package configurations;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeSuite;

public class TestConfiguration {
    @BeforeSuite
    public void addToken() {
        var baseUrl = System.getenv("BASE_URL");
        var token = System.getenv("TOKEN");


        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri(baseUrl).
                addHeader("Authorization", "Bearer " + token).
                build();
    }
}

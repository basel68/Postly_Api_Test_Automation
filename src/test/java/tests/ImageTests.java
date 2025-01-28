package tests;

import configurations.TestConfiguration;
import io.restassured.RestAssured;
import models.ImageModel;
import org.example.ApiServer;
import org.example.DataFaker;
import org.example.Helpers;
import org.example.Validators;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Date;
import java.util.Map;

public class ImageTests extends TestConfiguration {
    @Test
    public void getAllImages() {
        var response = ApiServer.Get(
                Map.of(),
                Map.of(),
                Map.of(),
                "admin/Images"
        );
        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);

        Validators.softAssert.assertAll();
    }
    @Test(enabled = false)
    public void addImage() {
        ImageModel image =  ImageModel
                .builder()
                .title(DataFaker.getRandomName())
                .url(DataFaker.getRandomUrl())
                .dateCreated(Helpers.formatDateToISO8601(new Date()))
                .fileName(DataFaker.getRandomName())
                .fileExtension("jpg")
                .build();
        var baseUrL=System.getenv("BASE_URL");
        var response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded") // Use form-data content type
                .formParam("file","")
                .formParam("title", image.getTitle()) // Add title
                .formParam("url", image.getUrl()) // Add URL
                .formParam("dateCreated", image.getDateCreated()) // Add dateCreated
                .formParam("fileName", image.getFileName()) // Add fileName
                .formParam("fileExtension", image.getFileExtension()) // Add fileExtension
                .when()
                .post(baseUrL+"/admin/Images");
        System.out.println(response.then().log().all());
        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);
        Validators.softAssert.assertAll();
    }
}

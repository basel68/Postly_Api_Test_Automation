package tests;

import configurations.TestConfiguration;
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

        Validators.assertAll();
    }
//    @Test
//    public void addImage() {
//        ImageModel image =  ImageModel
//                .builder()
//                .title(DataFaker.getRandomName())
//                .url(DataFaker.getRandomUrl())
//                .dateCreated(Helpers.formatDateToISO8601(new Date()))
//                .fileName(DataFaker.getRandomName())
//                .fileExtension("jpg")
//                .build();
//        var response = ApiServer.Post(
//                Map.of("Content-Type", "application/json"),
//                Map.of(),
//                Map.of(),
//                "admin/Images",
//                image
//        );
//        System.out.println(response.then().log().all());
//        Validators.softAssert = new SoftAssert();
//        Validators.validateStatusCode(200, response);
//        Validators.validateResponseTime(1500, response);
//        Validators.assertAll();
//    }
}

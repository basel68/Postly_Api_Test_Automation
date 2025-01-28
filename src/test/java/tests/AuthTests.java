package tests;

import org.example.ApiServer;
import org.example.DataFaker;
import org.example.Validators;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class AuthTests {
    @Test
    public void login(){
        var email=System.getenv("email");
        var password=System.getenv("password");
        var baseUrL=System.getenv("BASE_URL");
        var response= ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                baseUrL+"/admin/Auth/login",
                Map.of(
                        "email", email,
                        "password", password
                )
        );
        Validators.softAssert=new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);

        Validators.softAssert.assertAll();
    }
    @Test
    public void register(){
        var email= DataFaker.getRandomMail();
        var password="135790";
        var baseUrL=System.getenv("BASE_URL");
        var response= ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                baseUrL+"/admin/Auth/register",
                Map.of(
                        "email", email,
                        "password", password
                )
        );
        Validators.softAssert=new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);

        Validators.softAssert.assertAll();
    }
}

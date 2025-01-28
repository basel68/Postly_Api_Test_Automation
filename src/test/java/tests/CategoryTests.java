package tests;

import configurations.TestConfiguration;
import models.CategoryModel;
import org.example.ApiServer;
import org.example.DataFaker;
import org.example.Validators;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class CategoryTests extends TestConfiguration {

    @Test
    public void getAllCategories() {
        var response = ApiServer.Get(
                Map.of(),
                Map.of(),
                Map.of(),
                "admin/Categories"
        );
        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);

        Validators.softAssert.assertAll();
    }
    @Test
    public void getCategoryById() {
        CategoryModel category = CategoryModel.builder().build();
        category.setName(DataFaker.getRandomName());
        category.setUrlHandle(DataFaker.getRandomUrl());
        var response = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/Categories",
                category
        );
        CategoryModel categoryReceived = response.as(CategoryModel.class);
        var getCategoryByIdResponse = ApiServer.Get(
                Map.of("Content-Type", "application/json"),
                Map.of("id", categoryReceived.getId()),
                Map.of(),
                "admin/Categories/{id}"

        );
        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, getCategoryByIdResponse);
        Validators.validateResponseTime(1500, getCategoryByIdResponse);

        var deleteResponse = ApiServer.Delete(
                Map.of("Content-Type", "application/json"),
                Map.of("id", categoryReceived.getId()),
                Map.of(),
                "admin/Categories/{id}"
        );
        Validators.softAssert.assertAll();
    }

    @Test
    public void createCategory() {
        CategoryModel category = CategoryModel.builder().build();
        category.setName(DataFaker.getRandomName());
        category.setUrlHandle(DataFaker.getRandomUrl());
        var response = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/Categories",
                category
        );
        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);
        //make sure the response matches the req generated
        CategoryModel categoryReceived = response.as(CategoryModel.class);
        Validators.validateEqual(category.getName(), categoryReceived.getName(), "Category name is not the same between the request and response");
        Validators.validateEqual(category.getUrlHandle(), categoryReceived.getUrlHandle(), "Category urlhandle is not the same between the request and response");

        ApiServer.Delete(
                Map.of(),
                Map.of("id", categoryReceived.getId()),
                Map.of(),
                "admin/Categories/{id}"
        );
        Validators.softAssert.assertAll();
    }

    @Test
    public void updateCategory() {
        CategoryModel category = CategoryModel.builder().build();
        category.setName(DataFaker.getRandomName());
        category.setUrlHandle(DataFaker.getRandomUrl());
        var response = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/Categories",
                category
        );
        CategoryModel categoryReceived = response.as(CategoryModel.class);
        Validators.softAssert = new SoftAssert();
        Validators.validateEqual(category.getName(), categoryReceived.getName(), "Category name is not the same between the request and response");
        Validators.validateEqual(category.getUrlHandle(), categoryReceived.getUrlHandle(), "Category urlhandle is not the same between the request and response");

        category.setName(DataFaker.getRandomName());
        category.setUrlHandle(DataFaker.getRandomUrl());

        var updateResponse = ApiServer.Put(
                Map.of("Content-Type", "application/json"),
                Map.of("id", categoryReceived.getId()),
                Map.of(),
                "admin/Categories/{id}",
                category
        );
        categoryReceived = updateResponse.as(CategoryModel.class);

        Validators.validateEqual(category.getName(), categoryReceived.getName(), "Category name is not updated correctly");
        Validators.validateEqual(category.getUrlHandle(), categoryReceived.getUrlHandle(), "Category urlhandle is not updated correctly");

        var deleteResponse = ApiServer.Delete(
                Map.of(),
                Map.of("id", categoryReceived.getId()),
                Map.of(),
                "admin/Categories/{id}"
        );

        Validators.softAssert.assertAll();
    }

    @Test
    public void deleteCategory() {
        CategoryModel category = CategoryModel.builder().build();
        category.setName(DataFaker.getRandomName());
        category.setUrlHandle(DataFaker.getRandomUrl());
        var response = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/Categories",
                category
        );
        CategoryModel categoryReceived = response.as(CategoryModel.class);
        var deleteResponse = ApiServer.Delete(
                Map.of(),
                Map.of("id", categoryReceived.getId()),
                Map.of(),
                "admin/Categories/{id}"
        );
        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, deleteResponse);
        Validators.validateResponseTime(1500, deleteResponse);
        Validators.softAssert.assertAll();
    }

}

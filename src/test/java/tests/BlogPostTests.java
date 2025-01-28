package tests;

import configurations.TestConfiguration;
import models.BlogPostModel;
import models.BlogPostRequestModel;
import models.CategoryModel;
import org.example.ApiServer;
import org.example.DataFaker;
import org.example.Helpers;
import org.example.Validators;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


public class BlogPostTests extends TestConfiguration {

    @Test
    public void getAllBlogPosts() {
        var response = ApiServer.Get(
                Map.of(),
                Map.of(),
                Map.of(),
                "admin/BlogPosts"
        );
        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);

        Validators.softAssert.assertAll();
    }

    @Test
    public void getBlogPostById() {
        BlogPostRequestModel blogPost = createSampleBlogPost();

        var postResponse = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/BlogPosts",
                blogPost
        );

        BlogPostModel blogPostReceived = postResponse.as(BlogPostModel.class);

        var getResponse = ApiServer.Get(
                Map.of("Content-Type", "application/json"),
                Map.of("id", blogPostReceived.getId()),
                Map.of(),
                "admin/BlogPosts/{id}"
        );

        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, getResponse);
        Validators.validateResponseTime(1500, getResponse);

        deleteBlogPost(blogPostReceived.getId());
        Validators.softAssert.assertAll();
    }

    @Test
    public void createBlogPost() {
        BlogPostRequestModel blogPost = createSampleBlogPost();

        var response = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/BlogPosts",
                blogPost
        );

        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, response);
        Validators.validateResponseTime(1500, response);

        BlogPostModel blogPostReceived = response.as(BlogPostModel.class);
        Validators.validateEqual(blogPost.getTitle(), blogPostReceived.getTitle(), "BlogPost title mismatch");
        Validators.validateEqual(blogPost.getUrlHandle(), blogPostReceived.getUrlHandle(), "BlogPost URL handle mismatch");

        deleteBlogPost(blogPostReceived.getId());
        Validators.softAssert.assertAll();
    }

    @Test
    public void updateBlogPost() {
        BlogPostRequestModel blogPost = createSampleBlogPost();

        var postResponse = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/BlogPosts",
                blogPost
        );

        BlogPostModel blogPostReceived = postResponse.as(BlogPostModel.class);

        Validators.softAssert = new SoftAssert();
        Validators.validateEqual(blogPost.getTitle(), blogPostReceived.getTitle(), "BlogPost title mismatch");
        Validators.validateEqual(blogPost.getUrlHandle(), blogPostReceived.getUrlHandle(), "BlogPost URL handle mismatch");

        // Update the blog post
        blogPost.setTitle(DataFaker.getRandomName());
        blogPost.setUrlHandle(DataFaker.getRandomUrl());

        var updateResponse = ApiServer.Put(
                Map.of("Content-Type", "application/json"),
                Map.of("id", blogPostReceived.getId()),
                Map.of(),
                "admin/BlogPosts/{id}",
                blogPost
        );

        BlogPostModel updatedBlogPost = updateResponse.as(BlogPostModel.class);
        Validators.validateEqual(blogPost.getTitle(), updatedBlogPost.getTitle(), "BlogPost title not updated correctly");
        Validators.validateEqual(blogPost.getUrlHandle(), updatedBlogPost.getUrlHandle(), "BlogPost URL handle not updated correctly");

        deleteBlogPost(updatedBlogPost.getId());
        Validators.softAssert.assertAll();
    }

    @Test
    public void deleteBlogPost() {
        BlogPostRequestModel blogPost = createSampleBlogPost();
        System.out.println(Arrays.toString(blogPost.getCategories()));
        var postResponse = ApiServer.Post(
                Map.of("Content-Type", "application/json"),
                Map.of(),
                Map.of(),
                "admin/BlogPosts",
                blogPost
        );
        System.out.println(postResponse.then().log().all());
        BlogPostModel blogPostReceived = postResponse.as(BlogPostModel.class);

        var deleteResponse = ApiServer.Delete(
                Map.of(),
                Map.of("id", blogPostReceived.getId()),
                Map.of(),
                "admin/BlogPosts/{id}"
        );

        Validators.softAssert = new SoftAssert();
        Validators.validateStatusCode(200, deleteResponse);
        Validators.validateResponseTime(1500, deleteResponse);
        Validators.softAssert.assertAll();
    }

    // Helper method to create a sample BlogPostModel with real categories
    private BlogPostRequestModel createSampleBlogPost() {

        var response= ApiServer.Get(
                Map.of(),
                Map.of(),
                Map.of(),
                "admin/Categories"
        );
        CategoryModel[] categories=response.as(CategoryModel[].class);
        return BlogPostRequestModel.builder()
                .title(DataFaker.getRandomName())
                .shortDescription(DataFaker.getRandomName())
                .content(DataFaker.getRandomName())
                .featuredImageUrl(DataFaker.getRandomUrl())
                .urlHandle(DataFaker.getRandomUrl())
                .publishedDate(Helpers.formatDateToISO8601(new Date()))
                .author(DataFaker.getRandomName())
                .isVisible(true)
                .categories(new String[]{
                        categories[0].getId(),
                        categories[1].getId()
                })
                .build();

    }

    // Helper method to delete a blog post by ID
    private void deleteBlogPost(String id) {
        ApiServer.Delete(
                Map.of(),
                Map.of("id", id),
                Map.of(),
                "admin/BlogPosts/{id}"
        );
    }
}

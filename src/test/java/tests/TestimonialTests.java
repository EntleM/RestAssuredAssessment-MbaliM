package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import commons.Paths;
import payloadBuilder.UserPayload;
import requestBuilder.AdminRequestBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestimonialTests {

    private static String userID;


    @Test(priority = 1)
    public void createTestimonialTest() {
        String jsonBody = UserPayload.createTestimonialPayload(
                "Great",
                "What an awesome journey so far!",
                5,
                true
        ).toJSONString();

        Response response = given()
                .spec(AdminRequestBuilder.getAdminSpec())
                .body(jsonBody)
                .when()
                .post(Paths.TESTIMONIALS);

        response.then()
                .statusCode(201)
                .body("data.Id", notNullValue());

        userID = response.jsonPath().getString("data.Id");
        System.out.println("--- Saved Testimonial ID from API: " + userID + " ---");
    }

    @Test(priority = 2)
    public void updateTestimonialTest() {
        if (userID == null) {
            System.out.println("Skipping update test: No userID was captured from Test 1.");
            return;
        }

        String jsonBody = UserPayload.updateTestimonialPayload(
                "Updated Title",
                "Updated testimonial content",
                5
        ).toJSONString();

        given()
                .spec(AdminRequestBuilder.getAdminSpec())
                .body(jsonBody)
                .pathParam("id", userID)
                .when()
                .put(Paths.TESTIMONIALS + "/{id}")
                .then()
                .statusCode(200)
                .body("data.Title", equalTo("Updated Title"))
                .body("data.Content", equalTo("Updated testimonial content"));
    }

    @Test(priority = 3)
    public void deleteTestimonialTest() {
        if (userID == null) {
            System.out.println("Skipping delete test: No userID was captured from Test 1.");
            return;
        }

        given()
                .spec(AdminRequestBuilder.getAdminSpec())
                .pathParam("id", userID)
                .when()
                .delete(Paths.TESTIMONIALS + "/{id}")
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    // Create Testimonial - Invalid rating - Negative Validation Path
    @Test(priority = 4)
    public void createTestimonialInvalidRatingTest() {
        String jsonBody = UserPayload.createTestimonialPayload(
                "string",
                "string",
                10,
                true
        ).toJSONString();

        given()
                .spec(AdminRequestBuilder.getAdminSpec())
                .body(jsonBody)
                .when()
                .post(Paths.TESTIMONIALS)
                .then()
                .statusCode(400)
                .body("message", equalTo("Rating must be between 1 and 5"));
    }
}
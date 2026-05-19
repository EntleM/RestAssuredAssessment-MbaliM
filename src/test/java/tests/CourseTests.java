package tests;

import org.testng.annotations.Test;
import commons.Paths;
import requestBuilder.UserRequestBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CourseTests {

    @Test
    public void getCourseTest() {
        given()
                .spec(UserRequestBuilder.getUserSpec())
                .queryParam("category", "automation")
                .queryParam("level", "beginner")
                .when()
                .get(Paths.COURSES)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }
}
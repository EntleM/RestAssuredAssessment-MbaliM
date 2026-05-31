package requestBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import commons.Paths;

public class AdminRequestBuilder {

    private static final String ADMIN_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIxMmFlNTZkZi1iNGMyLTRlYTUtYmI3ZS1lMzNiMThiN2IyYjkiLCJlbWFpbCI6ImFkbWluQGdtYWlsLmNvbSIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTc4MDIxNTM2OSwiZXhwIjoxNzgwMzAxNzY5fQ._P3zD1R5t6cCyYkpDDrPdlJcGogmYm0m0hUELZhYzcw";

    public static RequestSpecification getAdminSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Paths.BASE_URL)
                .addHeader("Authorization", "Bearer " + ADMIN_TOKEN)
                .addHeader("Content-Type", "application/json") // Using a plain string avoids dependency errors
                .build();
    }
}